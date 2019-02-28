package com.seating.plan.generator.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seating.plan.generator.system.service.CityService;
import com.seating.plan.generator.system.util.RestResponse;
import com.seating.plan.generator.system.view.CityRespView;

@RestController
@RequestMapping("/cities")
public class CityController {

	private static final Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/{stateId}", method = RequestMethod.GET, produces = "application/json")
	public RestResponse findByStateId(@PathVariable("stateId") Integer stateId) {
		try {
			logger.info("successfully fetched all Cities");
			List<CityRespView> cityViewList = cityService.findAll(stateId);
			return RestResponse.build().withSuccess().withData(cityViewList);

		} catch (Exception e) {
			logger.error("Failed to fetch citites due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to fetch Cities due to:" + e.getMessage());
		}
	}
}
