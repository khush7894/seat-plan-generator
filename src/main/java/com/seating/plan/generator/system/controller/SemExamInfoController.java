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
import com.seating.plan.generator.system.entity.SubjectTimetable;
import com.seating.plan.generator.system.service.SemExamInfoService;
import com.seating.plan.generator.system.util.RestResponse;

@RestController
@RequestMapping("/examName")
public class SemExamInfoController {

	@Autowired
	private SemExamInfoService examInfoService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(SemExamInfoController.class);
	
	@RequestMapping(value = "/bulkInsert", method = RequestMethod.POST, headers = ("content-type=multipart/*"), produces = "application/json", consumes = "application/json")
	public RestResponse saveSemTimeTable(@RequestParam(value = "examNameFile", required = true)  MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
		try {

			String message = examInfoService.bulkSaveSemTimeTableInfo(file);
			logger.info(message);
			return RestResponse.build().withSuccess(message);

		} catch (Exception e) {
			logger.error("Failed to add bulk exam ifo due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to add bulk exam info due to:" + e.getMessage());
		}

}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public RestResponse findAll() {
		try {
			logger.info("find all roles");
			List<SemExamInfo> semExamInfoList = examInfoService.findAll();
			if (!semExamInfoList.isEmpty()) {
				logger.info("find all semester exam info successfully");
				return RestResponse.build().withSuccess().withData(semExamInfoList);
			} else {
				return RestResponse.build().withError("no data found");
			}
		} catch (Exception e) {
			logger.error("Failed to find semester exam info due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find semester exam info due to:" + e.getMessage());
		}
	}

	@RequestMapping( method = RequestMethod.DELETE, produces = "application/json")
	public RestResponse deleteStudentSubjectInfo(HttpServletRequest request, HttpServletResponse response) {
		try {

			examInfoService.deleteExamInfo();
			logger.info("Data deleted successfully");
			return RestResponse.build().withSuccess("Data deleted successfully");

		} catch (Exception e) {
			logger.error("Failed to delete bulk student due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to delete bulk student due to:" + e.getMessage());
		}

	}
	
	
}
