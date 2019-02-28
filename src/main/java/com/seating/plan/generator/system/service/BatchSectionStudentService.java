package com.seating.plan.generator.system.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.Subject;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seating.plan.generator.system.controller.BatchSectionStudentController;
import com.seating.plan.generator.system.entity.Batch;
import com.seating.plan.generator.system.entity.BatchSectionStudent;
import com.seating.plan.generator.system.entity.LkpSemester;
import com.seating.plan.generator.system.entity.Student;
import com.seating.plan.generator.system.repo.BatchRepo;
import com.seating.plan.generator.system.repo.BatchSectionStudentRepo;
import com.seating.plan.generator.system.repo.LkpSemesterRepo;
import com.seating.plan.generator.system.repo.StudentRepo;
import com.seating.plan.generator.system.req.web.view.StudentReqView;
import com.seating.plan.generator.system.req.web.view.UserReqView;
import com.seating.plan.generator.system.resp.mob.view.BatchSectionStudentRespView;
import com.seating.plan.generator.system.util.DateHelper;

@Transactional
@Service
public class BatchSectionStudentService {
	private static final Logger logger = LoggerFactory.getLogger(BatchSectionStudentService.class);

	@Autowired(required = true)
	private BatchSectionStudentRepo batchSectionStudentRepo;

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private BatchRepo batchRepo;

	@Autowired
	private LkpSemesterRepo lkpSemesterRepo;

	public String save(Integer batchId, Integer lkpSemesterId, MultipartFile file) throws IOException {

		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		logger.info("file extension:{} ", fileExtension);

		List<Student> studentList = new ArrayList<>();

		InputStream fileInputStream = file.getInputStream();

		try (XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;
			Iterator<Row> rows = sheet.rowIterator();
			Student student = null;

			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				if (row.getRowNum() == 0) {

					continue;
				}
				Iterator<Cell> cells = row.cellIterator();
				// if (row.getCell(1) != null || row.getCell(3) != null) {
				student = new Student();
				student.setCreatedDate(DateHelper.getCurrentDate());
				student.setIsDeleted(0);

				while (cells.hasNext()) {
					cell = (XSSFCell) cells.next();
					if (cell.getCellTypeEnum() == CellType.STRING) {
						if (0 == cell.getColumnIndex()) {

						} else if (1 == cell.getColumnIndex()) {
							student.setFirstName(cell.getStringCellValue());

						} else if (2 == cell.getColumnIndex()) {
							student.setLastName(cell.getStringCellValue());

						} else if (3 == cell.getColumnIndex()) {
							student.setMotherName(cell.getStringCellValue());

						} else if (4 == cell.getColumnIndex()) {
							student.setFatherName(cell.getStringCellValue());

						} else if (5 == cell.getColumnIndex()) {
							student.setEmail(cell.getStringCellValue());

						} else if (6 == cell.getColumnIndex()) {
							if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
								student.setContactOne(cell.getStringCellValue());
							}
							if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
								student.setContactOne(String.valueOf(cell.getNumericCellValue()));
							}
							System.out.println(cell.getNumericCellValue());

						} else if (7 == cell.getColumnIndex()) {
							if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								student.setRollNumber(cell.getStringCellValue());
							}
							if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								student.setRollNumber(String.valueOf(cell.getNumericCellValue()));
							}

						} else if (8 == cell.getColumnIndex()) {
							if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								student.setEnrollmentNo(cell.getStringCellValue());
							}
							if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								student.setEnrollmentNo(String.valueOf(cell.getNumericCellValue()));
							}

						}
					}
				}
				// }
				logger.info("firstName:{}, lastName:{}, email:{}, contactOne:{}, RoleNo:{}, EnrollmentNo:{}", student.getFirstName(), student.getLastName(), student.getEmail(),
						student.getContactOne(), student.getRollNumber(), student.getEnrollmentNo());

				logger.info("Fetching batch entity for lkp-Semester-Id:{}", lkpSemesterId);
				LkpSemester lkpSemester = lkpSemesterRepo.findOne(lkpSemesterId);

				logger.info("Fetching batch entity for batch-id:{}", batchId);
				Batch batch = batchRepo.findOne(batchId);

				Student savedStudent = studentRepo.save(student);

				BatchSectionStudent batchSectionStudent = new BatchSectionStudent();
				batchSectionStudent.setLkpSemester(lkpSemester);
				batchSectionStudent.setBatch(batch);
				batchSectionStudent.setStudent(savedStudent);

				batchSectionStudent.setCreatedDate(DateHelper.getCurrentDate());
				batchSectionStudent.setIsDeleted(0);

				batchSectionStudentRepo.save(batchSectionStudent);

			}
		}

		return "Bulk student upload sucessfully.";
	}

	public List<Student> findByBatchId(Integer batchId) {

		List<Student> studentList = studentRepo.findByStudnet(batchId);
		
		
		return studentList;

	}

}
