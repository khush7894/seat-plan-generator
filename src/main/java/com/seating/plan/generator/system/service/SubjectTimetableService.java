package com.seating.plan.generator.system.service;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.seating.plan.generator.system.entity.City;
import com.seating.plan.generator.system.entity.Department;
import com.seating.plan.generator.system.entity.Role;
import com.seating.plan.generator.system.entity.SemExamInfo;
import com.seating.plan.generator.system.entity.StudentSubjectInfo;
import com.seating.plan.generator.system.entity.SubjectInfo;
import com.seating.plan.generator.system.entity.SubjectTimetable;
import com.seating.plan.generator.system.repo.SemExamInfoRepo;
import com.seating.plan.generator.system.repo.StudentSubjectInfoRepo;
import com.seating.plan.generator.system.repo.SubjectInfoRepo;
import com.seating.plan.generator.system.repo.SubjectTimetableRepo;
import com.seating.plan.generator.system.util.DateHelper;
import com.seating.plan.generator.system.util.RestResponse;
import com.seating.plan.generator.system.view.CityRespView;
import com.seating.plan.generator.system.view.DepartmentRespView;
import com.seating.plan.generator.system.view.SeatingPlanRawDataView;

@Service
public class SubjectTimetableService {

	@Autowired
	private SubjectTimetableRepo subjectTimetableRepo;

	@Autowired
	private SubjectInfoRepo subjectInfoRepo;

	@Autowired
	private SemExamInfoRepo semExamInfoRepo;

	@Autowired
	private StudentSubjectInfoRepo studentSubjectInfoRepo;

	private static final Logger logger = LoggerFactory.getLogger(SubjectTimetableService.class);

	public String bulkSaveSubjectTimeTableInfo(MultipartFile file) throws Exception {
		List<SubjectTimetable> subjectTimetableList = new ArrayList<SubjectTimetable>();

		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		logger.info("file extension:{} ", fileExtension);

		if (!"xlsx".equalsIgnoreCase(fileExtension)) {
			throw new Exception("Please upload excel file in xlsx format.");
		}

		subjectTimetableList = readXLSXFile(file);

		int rowSave = subjectTimetableRepo.save(subjectTimetableList).size();
		return "Bulk time table upload rows : " + rowSave + " added successfully.";

	}

	public List<SeatingPlanRawDataView> findByExamDate(Date examDate) {
		List<SubjectTimetable> subjectTimetableList = subjectTimetableRepo.findByExamDate(examDate);
		logger.info("Reading time table list: {}", subjectTimetableList.size());

		List<SeatingPlanRawDataView> seatingPlanRawDataViewList = new ArrayList<>();

		for (SubjectTimetable subjectTimetable : subjectTimetableList) {
			SeatingPlanRawDataView seatingPlanRawDataView = new SeatingPlanRawDataView();
			seatingPlanRawDataView.setSubjectCode(subjectTimetable.getSubjectCode());
			seatingPlanRawDataView.setSubjectName(subjectTimetable.getSubjectName());
			seatingPlanRawDataView.setShift(subjectTimetable.getShift());

			SubjectInfo subjectInfo = subjectInfoRepo.findbySubjectCode(subjectTimetable.getSubjectCode());
			if (subjectInfo != null) {
				logger.info("Reading subject info: {}", subjectInfo.getExctrlC6());
				SemExamInfo examInfo = semExamInfoRepo.findbyExctrt(subjectInfo.getExctrlC6());
				logger.info("Reading examInfo: {}", examInfo);
				seatingPlanRawDataView.setExamName(examInfo.getExamNameC40());
			}
			List<StudentSubjectInfo> studentSubjectInfoList = studentSubjectInfoRepo
					.findbySubjectCode("%" + subjectTimetable.getSubjectCode().trim() + "%");

			List<String> rollNumberList = new ArrayList<>();
			for (StudentSubjectInfo studentSubjectInfo : studentSubjectInfoList) {
				rollNumberList.add(studentSubjectInfo.getRollNumber());
			}
			seatingPlanRawDataView.setRollNumberList(rollNumberList);
			seatingPlanRawDataViewList.add(seatingPlanRawDataView);
		}

		logger.info("Reading time table list after for : {}", subjectTimetableList.size());
		return seatingPlanRawDataViewList;
	}

	private List<SubjectTimetable> readXLSXFile(MultipartFile file) throws Exception {

		logger.info("Reading bulk student excel file: {}", file.getOriginalFilename());

		InputStream fileInputStream = file.getInputStream();
		List<SubjectTimetable> subjectTimetableList = new ArrayList<SubjectTimetable>();

		try (XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			Iterator<Row> rows = sheet.rowIterator();
			SubjectTimetable subjectTimetable = null;

			int rowNumber = 0;

			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();

				rowNumber++;
				if (row.getRowNum() == 0) {
					// getExcelValidation(row, studentExcelHeader);
					continue;
				}

				if (row.getCell(1) != null || row.getCell(3) != null) {
					subjectTimetable = new SubjectTimetable();

					subjectTimetable.setSubjectCode(getCellValue(row.getCell(0), "subjectcode", false, rowNumber));
					subjectTimetable.setSubjectName(getCellValue(row.getCell(1), "subjectname", false, rowNumber));
					subjectTimetable.setExamDate(
							DateHelper.stringToDate(getCellValue(row.getCell(2), "examdate", false, rowNumber), "dd-MM-yyyy"));
					subjectTimetable.setShift(getCellValue(row.getCell(3), "Shift", false, rowNumber));
					subjectTimetable.setCreatedDate(DateHelper.getCurrentDate());
					subjectTimetableList.add(subjectTimetable);
				}
			}

		}

		return subjectTimetableList;

	}

	private String getCellValue(XSSFCell cell, String columnName, boolean isMandatory, int rowNumber) throws Exception {
		String data = null;

		if (cell == null && isMandatory) {
			String errMsg = "Please provide '" + columnName + "' in row no.: " + rowNumber;
			logger.info("Excel cell validation rule failed : {}", errMsg);
			throw new Exception("Excel cell validation rule failed: " + errMsg);
		}

		if (cell != null) {
			if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				data = cell.getStringCellValue();

			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				data = String.valueOf((long) cell.getNumericCellValue());
		}
		if (StringUtils.isBlank(data) && isMandatory) {
			String errMsg = "Please provide '" + columnName + "' in row no.: " + rowNumber;
			logger.info("Excel cell validation rule failed : {}", errMsg);
			throw new Exception("Excel cell validation rule failed: " + errMsg);
		}
		return (data != null) ? data.trim() : data;
	}

	public void deleteSubjectTimetable() throws Exception {
		if (subjectTimetableRepo.count() != 0) {
			subjectTimetableRepo.deleteAll();
		}
		return;
	}

	public List<SubjectTimetable> findAll() {
			return subjectTimetableRepo.findAll();
		}


}
