package com.seating.plan.generator.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seating.plan.generator.system.entity.UserInstitute;
import com.seating.plan.generator.system.service.UserInstituteService;
import com.seating.plan.generator.system.util.RestResponse;

@RestController
@RequestMapping("/userinstitutes")
public class UserInstituteController {

	private static final Logger logger = LoggerFactory.getLogger(UserInstituteController.class);

	@Autowired
	private UserInstituteService userInstituteService;

	// By Id
	@RequestMapping(value = "/ById/{id}", method = RequestMethod.GET, produces = "application/json")
	public RestResponse findAllById(@PathVariable("id") Integer id) {
		try {
			logger.info("find all userinstitutes successfully");
			UserInstitute userInstitute = userInstituteService.findById(id);
			return RestResponse.build().withSuccess().withData(userInstitute);

		} catch (Exception e) {
			logger.error("Failed to create userinstitute due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to create userinstitute due to:" + e.getMessage());
		}
	}

	// By UserId
	@RequestMapping(value = "/ByUserId/{id}", method = RequestMethod.GET, produces = "application/json")
	public RestResponse findAllByUserId(@PathVariable("id") Integer id) {
		try {
			logger.info("find all userinstitutes successfully");
			List<UserInstitute> userInstitute = userInstituteService.findByUserId(id);
			return RestResponse.build().withSuccess().withData(userInstitute);

		} catch (Exception e) {
			logger.error("Failed to create userinstitute due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to create userinstitute due to:" + e.getMessage());
		}
	}

	// By Delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public RestResponse delete(@PathVariable("id") Integer id) {
		try {
			logger.info("delete userinstitue by id:{}", id);
			int count = userInstituteService.markDeleted(id);
			if (count > 0) {
				return RestResponse.build().withSuccess("userinstitutes deleted successfully.");
			} else {
				return RestResponse.build().withSuccess("userinstitutes not deleted successfully");
			}

		} catch (Exception e) {
			logger.error("Failed to retrive userinstitutes due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to retrive userinstitutes due to:" + e.getMessage());
		}
	}
}
