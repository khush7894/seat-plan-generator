package com.seating.plan.generator.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seating.plan.generator.system.entity.LkpCourse;
import com.seating.plan.generator.system.service.LKPCourseService;
import com.seating.plan.generator.system.util.RestResponse;

@RestController
@RequestMapping("/lkpCourse")
public class LKPCourseController {

	private static final Logger logger = LoggerFactory.getLogger(LKPCourseController.class);

	@Autowired
	private LKPCourseService lkpCourseService;

	@RequestMapping(method = RequestMethod.GET)
	public RestResponse findByStateId() {
		try {
			logger.info("Fetching all lkp Course.");
			List<LkpCourse> lkpCourseList = lkpCourseService.findAll();
			return RestResponse.build().withSuccess().withData(lkpCourseList);

		} catch (Exception e) {
			logger.error("Failed to fetch course due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to fetch course due to:" + e.getMessage());
		}
	}
}
