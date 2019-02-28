package com.seating.plan.generator.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seating.plan.generator.system.entity.LkpDepartment;
import com.seating.plan.generator.system.service.LKPDepartmentService;
import com.seating.plan.generator.system.util.RestResponse;

@RestController
@RequestMapping("/lkpDepartment")
public class LKPDepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(LKPDepartmentController.class);

	@Autowired
	private LKPDepartmentService lkpDepartmentService;

	@RequestMapping(method = RequestMethod.GET)
	public RestResponse findByStateId() {
		try {
			logger.info("Fetching all lkp department.");
			List<LkpDepartment> lkpDepartmentList = lkpDepartmentService.findAll();
			return RestResponse.build().withSuccess().withData(lkpDepartmentList);

		} catch (Exception e) {
			logger.error("Failed to fetch department due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to fetch department due to:" + e.getMessage());
		}
	}
}
