package com.seating.plan.generator.system.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import com.seating.plan.generator.system.entity.StudentSubjectInfo;
import com.seating.plan.generator.system.repo.StudentSubjectInfoRepo;
import com.seating.plan.generator.system.util.DateHelper;

@Service
public class StudentSubjectInfoService {
	

	@Autowired(required = true)
	StudentSubjectInfoRepo studentSubjectInfoRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(StudentSubjectInfoService.class);

	public String bulkSaveStudentSubjectInfo(MultipartFile file) throws Exception {
		List<StudentSubjectInfo> addBulkStudentSubjectInfoList = new ArrayList<StudentSubjectInfo>();

		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		logger.info("file extension:{} ", fileExtension);

		if (!"xlsx".equalsIgnoreCase(fileExtension)) {
			throw new Exception("Please upload excel file in xlsx format.");
		}

		addBulkStudentSubjectInfoList = readXLSXFile(file);

		// save student list
		int rowSave = studentSubjectInfoRepo.save(addBulkStudentSubjectInfoList).size();
		return "Bulk student : " + rowSave + " added successfully.";

	}

	
	private List<StudentSubjectInfo> readXLSXFile(MultipartFile file) throws Exception {

		logger.info("Reading bulk student excel file: {}", file.getOriginalFilename());

		InputStream fileInputStream = file.getInputStream();
		List<StudentSubjectInfo> addBulkStudentSubjectInfoList = new ArrayList<StudentSubjectInfo>();

		try (XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			Iterator<Row> rows = sheet.rowIterator();
			StudentSubjectInfo addBulkStudentSubjectInfo = null;

			int rowNumber = 0;

			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();

				rowNumber++;
				if (row.getRowNum() == 0) {
//					getExcelValidation(row, studentExcelHeader);
					continue;
				}

				if (row.getCell(1) != null || row.getCell(3) != null) {
					addBulkStudentSubjectInfo = new StudentSubjectInfo();

					addBulkStudentSubjectInfo.setExctrlC6(getCellValue(row.getCell(0), "EXCTRL,C,6", false, rowNumber));

					addBulkStudentSubjectInfo.setSubjectCodeC2(getCellValue(row.getCell(1), "SUBCODE,C,2", false, rowNumber));

					addBulkStudentSubjectInfo.setRollNumber(getCellValue(row.getCell(2), "ROLLNO,C,12", false, rowNumber));

					addBulkStudentSubjectInfo.setActSubjectC8(getCellValue(row.getCell(3), "ACTSUBCODE,C,8", false, rowNumber));

					addBulkStudentSubjectInfo.setEnrollmentC15(getCellValue(row.getCell(4), "ENROLLMENT,C,15", false, rowNumber));

					addBulkStudentSubjectInfo.setDetC1(getCellValue(row.getCell(5), "DET,C,1", false, rowNumber));
					
					addBulkStudentSubjectInfo.setCreatedDate(DateHelper.getCurrentDate());

					addBulkStudentSubjectInfoList.add(addBulkStudentSubjectInfo);
				}
			}

		}

		return addBulkStudentSubjectInfoList;

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

	public void deleteStudentSubjectInfo() throws Exception {
		if(studentSubjectInfoRepo.count()!=0){
			studentSubjectInfoRepo.deleteAll();
		}
		return;
	}


	public List<StudentSubjectInfo> findAll() {
		
		return studentSubjectInfoRepo.findAll();
	}
	
}
