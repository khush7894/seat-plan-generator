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

import com.seating.plan.generator.system.entity.SubjectInfo;
import com.seating.plan.generator.system.repo.SubjectInfoRepo;
import com.seating.plan.generator.system.util.DateHelper;

@Service
public class SubjectInfoService {

	@Autowired(required = true)
	SubjectInfoRepo subjectInfoRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(SubjectInfoService.class);

	
	public String bulkSaveStudentInfo(MultipartFile file) throws Exception {
		List<SubjectInfo> addBulkStudentInfoList = new ArrayList<SubjectInfo>();
		
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		logger.info("file extension:{} ", fileExtension);

		if (!"xlsx".equalsIgnoreCase(fileExtension)) { 
			throw new Exception("Please upload excel file in xlsx format.");
		}
		
		addBulkStudentInfoList = readXLSXFile(file);
		// save student list
		int rowSave = subjectInfoRepo.save(addBulkStudentInfoList).size();
		return "Bulk student : " + rowSave + " added successfully.";
		
	}


	public void deleteStudentInfo() throws Exception {
		if(subjectInfoRepo.count()!=0){
			subjectInfoRepo.deleteAll();
		}
		return;
	}

	private List<SubjectInfo> readXLSXFile(MultipartFile file) throws Exception {
		
		logger.info("Reading bulk student from excel file: {}", file.getOriginalFilename());
		
		InputStream fileInputStream = file.getInputStream();
		List<SubjectInfo> addBulkStudentInfoList = new ArrayList<SubjectInfo>();
		
	   try(XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)){
		   XSSFSheet sheet = wb.getSheetAt(0);
		   XSSFRow row;
		   Iterator<Row> rows = sheet.rowIterator();
		   SubjectInfo addBulkStudentInfo = null;
		   
		   int rowNumber = 0;
		   
		   while(rows.hasNext()){
			   row = (XSSFRow) rows.next();
			   rowNumber++;
			   if (row.getRowNum() == 0){
//				   getExcelValidation(row, studentExcelHeader);
				   continue;
			   }
			   
			   addBulkStudentInfo = new SubjectInfo();
			   addBulkStudentInfo.setExctrlC6(getCellValue(row.getCell(0), "EXCTRL,C,6", false, rowNumber));
			   addBulkStudentInfo.setSubcodeC2(getCellValue(row.getCell(1), "SUBCODE,C,2", false, rowNumber));
			   addBulkStudentInfo.setActsubcodeC7(getCellValue(row.getCell(2), "ACTSUBCODE,C,7", false, rowNumber));
			   addBulkStudentInfo.setSubdes1C35(getCellValue(row.getCell(3), "SUBDES1,C,35", false, rowNumber));
			   addBulkStudentInfo.setSubdess2C20(getCellValue(row.getCell(4), "SUBDES2,C,20", false, rowNumber));
			   addBulkStudentInfo.setCreatedDate(DateHelper.getCurrentDate());
			   addBulkStudentInfoList.add(addBulkStudentInfo);
		   }
		   return addBulkStudentInfoList;
	   }
		
	
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


	public List<SubjectInfo> findAll() {
		return subjectInfoRepo.findAll();
	}



}
