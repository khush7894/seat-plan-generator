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

import com.seating.plan.generator.system.entity.RoomInfo;
import com.seating.plan.generator.system.repo.RoomInfoRepo;
import com.seating.plan.generator.system.util.DateHelper;

@Service
public class RoomInfoService {

	@Autowired
	private RoomInfoRepo roomInfoRepo;

	private static final Logger logger = LoggerFactory.getLogger(SubjectTimetableService.class);

	public String bulkSaveRoomInfo(MultipartFile file) throws Exception {
		List<RoomInfo> roomInfoList = new ArrayList<RoomInfo>();

		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		logger.info("file extension:{} ", fileExtension);

		if (!"xlsx".equalsIgnoreCase(fileExtension)) {
			throw new Exception("Please upload excel file in xlsx format.");
		}

		roomInfoList = readXLSXFile(file);

		int rowSave = roomInfoRepo.save(roomInfoList).size();
		return "Bulk room info upload rows : " + rowSave + " added successfully.";

	}

	private List<RoomInfo> readXLSXFile(MultipartFile file) throws Exception {

		logger.info("Reading bulk student excel file: {}", file.getOriginalFilename());

		InputStream fileInputStream = file.getInputStream();
		List<RoomInfo> roomInfoList = new ArrayList<RoomInfo>();

		try (XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			Iterator<Row> rows = sheet.rowIterator();
			RoomInfo roomInfo = null;

			int rowNumber = 0;

			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();

				rowNumber++;
				if (row.getRowNum() == 0) {
					// getExcelValidation(row, studentExcelHeader);
					continue;
				}

				if (row.getCell(1) != null || row.getCell(3) != null) {
					roomInfo = new RoomInfo();

					roomInfo.setRoomName(getCellValue(row.getCell(1), "Room Name", false, rowNumber));
					roomInfo.setHalfQuantity(getCellValue(row.getCell(2), "Half Quantity", false, rowNumber));
					roomInfo.setFullQuantity(getCellValue(row.getCell(3), "Full Quantity", false, rowNumber));
					roomInfo.setCreatedDate(DateHelper.getCurrentDate());
					roomInfoList.add(roomInfo);
				}
			}

		}

		return roomInfoList;

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

	public void deleteRoomInfo() throws Exception {
		if (roomInfoRepo.count() != 0) {
			roomInfoRepo.deleteAll();
		}
		return;
	}

	public List<RoomInfo> findAll() {
			return roomInfoRepo.findAll();
		}
	
	
}
