package com.seating.plan.generator.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seating.plan.generator.system.entity.BatchSectionStudent;
import com.seating.plan.generator.system.entity.Student;
import com.seating.plan.generator.system.resp.mob.view.BatchSectionStudentRespView;
import com.seating.plan.generator.system.service.BatchSectionStudentService;
import com.seating.plan.generator.system.util.RestResponse;

@RestController
@RequestMapping("/batchSectionStudents")
public class BatchSectionStudentController {

	private static final Logger logger = LoggerFactory.getLogger(BatchSectionStudentController.class);

	@Autowired
	private BatchSectionStudentService batchSectionStudentService;

	@RequestMapping(value = "/bulkInsert", method = RequestMethod.POST, headers = ("content-type=multipart/*"), produces = "application/json", consumes = "application/json")
	public RestResponse bulkInsert(

			@RequestParam(value = "batch_id", required = false) Integer batchId, 
			@RequestParam(value = "lkp_semester", required = false) Integer lkpSemesterId,
			@RequestParam(value = "studentListFile", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

		try {

			logger.info("Student bulk upload File-Nmae:{}", file.getOriginalFilename());
			
			String message = batchSectionStudentService.save(batchId, lkpSemesterId, file);

			logger.info(message);
			return RestResponse.build().withSuccess(message);

		} catch (

		Exception e) {
			logger.error("Failed to add bulk student due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to add bulk student due to:" + e.getMessage());
		}
	}

	@RequestMapping(value = "/byBatch", method = RequestMethod.GET, produces = "application/json")
	public RestResponse findByBatchId(@RequestParam("batchId") Integer batchId) {
		try {
			Integer instituteId = 1;

			logger.info("find student by batchId:{}", batchId); // done by hod
			List<Student> studentList = batchSectionStudentService.findByBatchId(batchId);

			logger.info("Fetched student list size:{}", studentList.size());
			return RestResponse.build().withSuccess().withData(studentList);

		} catch (Exception e) {
			logger.error("Failed to find batchSectionStudent due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find batchSectionStudent due to:" + e.getMessage());
		}
	}

}
