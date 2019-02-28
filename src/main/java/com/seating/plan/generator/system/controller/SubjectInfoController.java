package com.seating.plan.generator.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seating.plan.generator.system.entity.SemExamInfo;
import com.seating.plan.generator.system.entity.StudentSubjectInfo;
import com.seating.plan.generator.system.entity.SubjectInfo;
import com.seating.plan.generator.system.service.SemExamInfoService;
import com.seating.plan.generator.system.service.StudentSubjectInfoService;
import com.seating.plan.generator.system.service.SubjectInfoService;
import com.seating.plan.generator.system.service.SubjectTimetableService;
import com.seating.plan.generator.system.util.RestResponse;

@RestController
@RequestMapping("/subjectInfo")
public class SubjectInfoController {

	@Autowired
	private SubjectInfoService subjectInfoService;

	private static final Logger logger = LoggerFactory.getLogger(SubjectInfoController.class);

	@RequestMapping(value = "/bulkInsert", method = RequestMethod.POST, headers = ("content-type=multipart/*"), produces = "application/json", consumes = "application/json")
	public RestResponse saveStudentInfo(@RequestParam(value = "subjectInfoFile", required = true) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			String message = subjectInfoService.bulkSaveStudentInfo(file);
			logger.info(message);
			return RestResponse.build().withSuccess(message);

		} catch (Exception e) {
			logger.error("Failed to add bulk student due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to add bulk student due to:" + e.getMessage());
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public RestResponse findAll() {
		try {
			logger.info("find all roles");
			List<SubjectInfo> subjectInfoList = subjectInfoService.findAll();
			if (!subjectInfoList.isEmpty()) {
				logger.info("find all student  info successfully");
				return RestResponse.build().withSuccess().withData(subjectInfoList);
			} else {
				return RestResponse.build().withError("no data found");
			}
		} catch (Exception e) {
			logger.error("Failed to find student info due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find student info due to:" + e.getMessage());
		}
	}
	
	@RequestMapping( method = RequestMethod.DELETE, produces = "application/json")
	public RestResponse deleteStudentInfo(HttpServletRequest request, HttpServletResponse response) {
		try {

			subjectInfoService.deleteStudentInfo();
			logger.info("Data deleted successfully");
			return RestResponse.build().withSuccess("Data deleted successfully");

		} catch (Exception e) {
			logger.error("Failed to delete bulk student due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to delete bulk student due to:" + e.getMessage());
		}

	}

}