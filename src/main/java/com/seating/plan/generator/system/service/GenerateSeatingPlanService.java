package com.seating.plan.generator.system.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seating.plan.generator.system.entity.Student;
import com.seating.plan.generator.system.file.SeatingPlanService;
import com.seating.plan.generator.system.repo.BatchSectionStudentRepo;
import com.seating.plan.generator.system.repo.CourseRepo;
import com.seating.plan.generator.system.repo.DepartmentRepo;
import com.seating.plan.generator.system.repo.InstituteRepo;
import com.seating.plan.generator.system.repo.StudentRepo;
import com.seating.plan.generator.system.req.web.view.SeatingPlanBatchReqView;
import com.seating.plan.generator.system.req.web.view.SeatingPlanGenerateReqView;
import com.seating.plan.generator.system.util.DateHelper;

@Transactional
@Service
public class GenerateSeatingPlanService {

	private static final Logger logger = LoggerFactory.getLogger(GenerateSeatingPlanService.class);

	@Autowired
	private InstituteRepo instituteRepo;

	@Autowired
	private DepartmentRepo departmentRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private SeatingPlanService seatingPlanService;

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private BatchSectionStudentRepo batchSectionStudentRepo;

	public List<Map<String, Object>> createSeatingPlan(SeatingPlanGenerateReqView seatingPlanGenerateReqView, MultipartFile inputFile) throws Exception {

		int totalStudent = 0;
		int roomCapacity = 0;

		List<SeatingPlanBatchReqView> seatingPlanBatchReqViewList = seatingPlanGenerateReqView.getBatchInfoViewArray();
		logger.info("Generate seating plan to total no. of batch :{} ", seatingPlanBatchReqViewList.size());
		//
		int totalSubject = seatingPlanBatchReqViewList.size();
		// List<Map<String, Object>> dataList = readFile(inputFile);
		// logger.info("student details at row 1 : {}", dataList.get(1));

		List<List<String>> studnetListList = new ArrayList<>();

		for (SeatingPlanBatchReqView seatingPlanBatchReqView : seatingPlanBatchReqViewList) {
			logger.info("Fetching student for subject code : {}", seatingPlanBatchReqView.getSubjectCode());

			studnetListList.add(seatingPlanBatchReqView.getRollNumber());

			logger.info("Fetching student list size : {}", seatingPlanBatchReqView.getRollNumber().size());

			totalStudent = totalStudent + seatingPlanBatchReqView.getRollNumber().size();

		}

		logger.info("student list for subject : {}", studnetListList.size());

		List<String> roomNameList = seatingPlanGenerateReqView.getRoomNameArray();
		List<Integer> studentCapacityList = seatingPlanGenerateReqView.getStudentCountArray();
		logger.info("Total No. of rooms:{} ", roomNameList.size());

		for (Integer integer : studentCapacityList) {
			roomCapacity = roomCapacity + integer;
		}

		if (roomCapacity < totalStudent) {
			throw new Exception("Total room capacity for seating is less than total allowable students.");
		}
		List<Map<String, Object>> seatingPlanList = new ArrayList<>();

		for (int i = 0; i < roomNameList.size(); i++) {

			Integer studentCapacityCount = studentCapacityList.get(i);
			int remainder = studentCapacityCount % totalSubject;
			Integer studentPerBatch = (studentCapacityCount - remainder) / totalSubject;

			for (int j = 0; j < totalSubject; j++) {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roomNo", roomNameList.get(i));
				
				if(seatingPlanBatchReqViewList.get(j).getDepartmentName() !=null) {
					map.put("departmentName", seatingPlanBatchReqViewList.get(j).getDepartmentName());	
				}
				
				if(seatingPlanBatchReqViewList.get(j).getCourseName() !=null) {
					map.put("courseName", seatingPlanBatchReqViewList.get(j).getCourseName());
				}
				
				if(seatingPlanBatchReqViewList.get(j).getExamName() !=null) {
					map.put("examName", seatingPlanBatchReqViewList.get(j).getExamName());
				}
				
				
				map.put("subjectCode", seatingPlanBatchReqViewList.get(j).getSubjectCode());
				map.put("subjectName", seatingPlanBatchReqViewList.get(j).getSubjectName());
				map.put("roomCapacity", String.valueOf(studentCapacityList));

				StringBuilder sb = new StringBuilder();

				boolean firstRollnumber = true;
				List<String> list = studnetListList.get(j);
				for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
					if (count == studentPerBatch) {
						break;
					}
					String next = iterator.next();
					String rollNumber = next;
					if (firstRollnumber) {
						sb.append(rollNumber);
						firstRollnumber = false;
					} else {
						sb.append(", ");
						sb.append(rollNumber.substring(Math.max(rollNumber.length() - 8, 0)));
					}
					count++;

					iterator.remove();
				}
				map.put("studentCount", String.valueOf(count));
				map.put("seating", sb.toString());
				seatingPlanList.add(map);

			}

		}
		logger.info("Final Output:{}", seatingPlanList);

		// String filePath =
		// seatingPlanService.createSeatingPlanFile(seatingPlanList,
		// DateHelper.dateToString(seatingPlanGenerateReqView.getExamDate(),
		// DateHelper.DATE_FORMAT_DEFAULT));

		// logger.info("File PAth : {}", filePath);

		return seatingPlanList;
	}

	// public List<Map<String, Object>> readFile(MultipartFile file) throws
	// IOException {
	// logger.info("Read file start");
	//
	// List<Map<String, Object>> studentList = new ArrayList<>();
	//
	// try (XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream())) {
	// XSSFSheet sheet = wb.getSheetAt(0);
	// XSSFRow row;
	// XSSFCell cell;
	// Iterator<Row> rows = sheet.rowIterator();
	// Student student = null;
	//
	// while (rows.hasNext()) {
	// row = (XSSFRow) rows.next();
	// if (row.getRowNum() == 0) {
	//
	// continue;
	// }
	// Map<String, Object> map = new HashMap<>();
	//
	// Iterator<Cell> cells = row.cellIterator();
	// // if (row.getCell(1) != null || row.getCell(3) != null) {
	// student = new Student();
	// student.setCreatedDate(DateHelper.getCurrentDate());
	// student.setIsDeleted(0);
	//
	// while (cells.hasNext()) {
	// cell = (XSSFCell) cells.next();
	// if (cell.getCellTypeEnum() == CellType.STRING) {
	//
	// if (0 == cell.getColumnIndex()) {
	// if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
	// map.put("id", cell.getStringCellValue());
	// }
	// if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
	// map.put("id", cell.getNumericCellValue());
	// }
	// } else if (2 == cell.getColumnIndex()) {
	// map.put("rollNumber", cell.getStringCellValue());
	//
	// } else if (3 == cell.getColumnIndex()) {
	// map.put("subjectCode", cell.getStringCellValue());
	//
	// }
	// }
	// }
	// studentList.add(map);
	//
	// }
	// }
	// logger.info("Row count : {}", studentList.size());
	//
	// logger.info("Read file end");
	// return studentList;
	// }
	//

	/*
	 * 
	 * Code for Pdf Generator
	 * 
	 * 
	 * public String createSeatingPlan(SeatingPlanGenerateReqView
	 * seatingPlanGenerateReqView, MultipartFile inputFile) throws Exception {
	 * 
	 * int totalStudent = 0; int roomCapacity = 0;
	 * 
	 * List<SeatingPlanBatchReqView> seatingPlanBatchReqViewList =
	 * seatingPlanGenerateReqView.getBatchInfoViewArray();
	 * logger.info("Generate seating plan to total no. of batch :{} ",
	 * seatingPlanBatchReqViewList.size());
	 * 
	 * int totalSubject = seatingPlanBatchReqViewList.size(); List<Map<String,
	 * Object>> dataList = readFile(inputFile);
	 * logger.info("student details at row 1 : {}", dataList.get(1));
	 * 
	 * List<List<Map<String, Object>>> studnetListList = new ArrayList<>();
	 * 
	 * for (SeatingPlanBatchReqView seatingPlanBatchReqView :
	 * seatingPlanBatchReqViewList) {
	 * logger.info("Fetching student for subject code : {}",
	 * seatingPlanBatchReqView.getSubjectCode());
	 * 
	 * List<Map<String, Object>> studnetList = new ArrayList<>();
	 * 
	 * for (Map<String, Object> map : dataList) { String subjectCode = (String)
	 * map.get("subjectCode"); if
	 * (subjectCode.equalsIgnoreCase(seatingPlanBatchReqView.getSubjectCode()))
	 * { studnetList.add(map); } }
	 * logger.info("Fetching student list size : {}", studnetList.size());
	 * 
	 * studnetListList.add(studnetList); totalStudent = totalStudent +
	 * studnetList.size();
	 * 
	 * }
	 * 
	 * logger.info("student list for subject : {}", studnetListList.size());
	 * 
	 * List<String> roomNameList =
	 * seatingPlanGenerateReqView.getRoomNameArray(); List<Integer>
	 * studentCapacityList = seatingPlanGenerateReqView.getStudentCountArray();
	 * logger.info("Total No. of rooms:{} ", roomNameList.size());
	 * 
	 * for (Integer integer : studentCapacityList) { roomCapacity = roomCapacity
	 * + integer; }
	 * 
	 * if (roomCapacity < totalStudent) { throw new
	 * Exception("Total room capacity for seating is less than total allowable students."
	 * ); } List<Map<String, Object>> seatingPlanList = new ArrayList<>();
	 * 
	 * for (int i = 0; i < roomNameList.size(); i++) {
	 * 
	 * Integer studentCapacityCount = studentCapacityList.get(i); int remainder
	 * = studentCapacityCount % totalSubject; Integer studentPerBatch =
	 * (studentCapacityCount - remainder) / totalSubject;
	 * 
	 * for (int j = 0; j < totalSubject; j++) { int count = 0; Map<String,
	 * Object> map = new HashMap<String, Object>(); map.put("roomNo",
	 * roomNameList.get(i)); map.put("subjectCode",
	 * seatingPlanBatchReqViewList.get(j).getSubjectCode());
	 * map.put("subjectName",
	 * seatingPlanBatchReqViewList.get(j).getSubjectName());
	 * map.put("roomCapacity", String.valueOf(studentCapacityList));
	 * 
	 * StringBuilder sb = new StringBuilder();
	 * 
	 * boolean firstRollnumber = true; List<Map<String, Object>> list =
	 * studnetListList.get(j); for (Iterator<Map<String, Object>> iterator =
	 * list.iterator(); iterator.hasNext();) { if (count == studentPerBatch) {
	 * break; } Map<String, Object> next = iterator.next(); String rollNumber =
	 * (String) next.get("rollNumber"); if (firstRollnumber) {
	 * sb.append(rollNumber); firstRollnumber = false; } else { sb.append(", ");
	 * sb.append(rollNumber.substring(Math.max(rollNumber.length() - 8, 0))); }
	 * count++;
	 * 
	 * iterator.remove(); } map.put("studentCount", String.valueOf(count));
	 * map.put("seating", sb.toString()); seatingPlanList.add(map);
	 * 
	 * }
	 * 
	 * } logger.info("Final Output:{}", seatingPlanList);
	 * 
	 * String filePath =
	 * seatingPlanService.createSeatingPlanFile(seatingPlanList,
	 * DateHelper.dateToString(seatingPlanGenerateReqView.getExamDate(),
	 * DateHelper.DATE_FORMAT_DEFAULT));
	 * 
	 * logger.info("File PAth : {}", filePath);
	 * 
	 * return filePath; }
	 */

}
