package com.seating.plan.generator.system.service;

import java.io.InputStream;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.seating.plan.generator.system.entity.SemExamInfo;
import com.seating.plan.generator.system.repo.SemExamInfoRepo;
import com.seating.plan.generator.system.util.DateHelper;

@Service
public class SemExamInfoService {
	
	@Autowired
	private SemExamInfoRepo examInfoRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(SemExamInfoService.class);


	public String bulkSaveSemTimeTableInfo(MultipartFile file) throws Exception {
		List<SemExamInfo> examInfoList = new ArrayList<SemExamInfo>();
		
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		logger.info("file extension:{} ", fileExtension);

		if (!"xlsx".equalsIgnoreCase(fileExtension)) {
			throw new Exception("Please upload excel file in xlsx format.");
		}

		examInfoList = readXLSXFile(file);

		// save student list
		int rowSave = examInfoRepo.save(examInfoList).size();
		return "Bulk exam info : " + rowSave + " added successfully.";

	}

	private List<SemExamInfo> readXLSXFile(MultipartFile file) throws Exception {

		logger.info("Reading bulk student excel file: {}", file.getOriginalFilename());

		InputStream fileInputStream = file.getInputStream();
		List<SemExamInfo> examInfoList = new ArrayList<SemExamInfo>();

		try (XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			Iterator<Row> rows = sheet.rowIterator();
			SemExamInfo semExamInfo = null;

			int rowNumber = 0;

			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();

				rowNumber++;
				if (row.getRowNum() == 0) {
//					getExcelValidation(row, studentExcelHeader);
					continue;
				}

				if (row.getCell(1) != null || row.getCell(3) != null) {
					semExamInfo = new SemExamInfo();

					semExamInfo.setExctrlC6(getCellValue(row.getCell(0), "EXCTRL,C,6", false, rowNumber));

					semExamInfo.setExamNameC40(getCellValue(row.getCell(1), "EXAMNAME,C,40", false, rowNumber));

					semExamInfo.setCreatedDate(DateHelper.getCurrentDate());
					
					examInfoList.add(semExamInfo);
				}
			}

		}

		return examInfoList;

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

	public void deleteExamInfo() throws Exception {
		if(examInfoRepo.count()!=0){
			examInfoRepo.deleteAll();
		}
		return;
	}

	public List<SemExamInfo> findAll() {
	
		return examInfoRepo.findAll();
	}
	
}
