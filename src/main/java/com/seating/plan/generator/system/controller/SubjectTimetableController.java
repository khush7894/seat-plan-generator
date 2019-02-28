package com.seating.plan.generator.system.controller;

import java.util.Date;
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

import com.seating.plan.generator.system.entity.Role;
import com.seating.plan.generator.system.entity.SubjectTimetable;
import com.seating.plan.generator.system.service.SubjectTimetableService;
import com.seating.plan.generator.system.util.DateHelper;
import com.seating.plan.generator.system.util.RestResponse;
import com.seating.plan.generator.system.view.DepartmentRespView;
import com.seating.plan.generator.system.view.SeatingPlanRawDataView;

@RestController
@RequestMapping("/subjectTimetable")
public class SubjectTimetableController {

	@Autowired
	private SubjectTimetableService subjectTimetableService;
	
	private static final Logger logger = LoggerFactory.getLogger(SubjectTimetableController.class);

	
	@RequestMapping(value = "/bulkInsert", method = RequestMethod.POST, headers = ("content-type=multipart/*"), produces = "application/json", consumes = "application/json")
	public RestResponse saveSubjectTimetable(@RequestParam(value = "subjectTimetableFile", required = true)  MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
		try {

			String message = subjectTimetableService.bulkSaveSubjectTimeTableInfo(file);
			logger.info(message);
			return RestResponse.build().withSuccess(message);

		} catch (Exception e) {
			logger.error("Failed to add bulk subject timetable info due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to add bulk subject timetable info due to:" + e.getMessage());
		}

}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public RestResponse findAll() {
		try {
			logger.info("find all subject timetable");
			List<SubjectTimetable> subjectTimetableList = subjectTimetableService.findAll();
			if (!subjectTimetableList.isEmpty()) {
				logger.info("find all subject timetable successfully");
				return RestResponse.build().withSuccess().withData(subjectTimetableList);
			} else {
				return RestResponse.build().withError("no data found");
			}
		} catch (Exception e) {
			logger.error("Failed to find all subject timetable due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find all subject timetable due to:" + e.getMessage());
		}
	}
	
	@RequestMapping( method = RequestMethod.DELETE, produces = "application/json")
	public RestResponse deleteStudentSubjectInfo(HttpServletRequest request, HttpServletResponse response) {
		try {

			subjectTimetableService.deleteSubjectTimetable();
			logger.info("Data deleted successfully");
			return RestResponse.build().withSuccess("Data deleted successfully");

		} catch (Exception e) {
			logger.error("Failed to delete bulk student due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to delete bulk student due to:" + e.getMessage());
		}

	}
	

	@RequestMapping(value = "/byExamDate", method = RequestMethod.GET)
	public RestResponse findByExamDate(@RequestParam(value = "examDate", required = true) String examDate) {
		try {
			
			logger.info("find time table by date:{}", examDate);
			List<SeatingPlanRawDataView> seatingPlanRawDataViewList = subjectTimetableService.findByExamDate(DateHelper.stringToDate(examDate, "yyyy/MM/dd"));
			if (!seatingPlanRawDataViewList.isEmpty()) {
				logger.info("Time table fetched successfully", seatingPlanRawDataViewList.size());
				return RestResponse.build().withSuccess().withData(seatingPlanRawDataViewList);
			} else {
				return RestResponse.build().withError("No data found");

			}

		} catch (Exception e) {
			logger.error("Failed to find time table due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find time table due to :" + e.getMessage());
		}

	}

	
	
}
