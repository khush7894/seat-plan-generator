package com.seating.plan.generator.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seating.plan.generator.system.service.LKPSemesterService;
import com.seating.plan.generator.system.util.RestResponse;
import com.seating.plan.generator.system.view.LKPSemesterRespView;

@RestController
@RequestMapping("/lkpSemesters")
public class LKPSemesterController {

	private static final Logger logger = LoggerFactory.getLogger(LKPSemesterController.class);

	@Autowired
	private LKPSemesterService lkpSemesterService;

	@RequestMapping(method = RequestMethod.GET)
	public RestResponse findAll() {
		try {
			logger.info("Fetching all lkp semenster.");
			List<LKPSemesterRespView> lkpSemesterRespViewList = lkpSemesterService.findAll();
			return RestResponse.build().withSuccess().withData(lkpSemesterRespViewList);

		} catch (Exception e) {
			logger.error("Failed to fetch semenster due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to fetch semenster due to:" + e.getMessage());
		}
	}
}
