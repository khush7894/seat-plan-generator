package com.seating.plan.generator.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seating.plan.generator.system.entity.Role;
import com.seating.plan.generator.system.service.RoleService;
import com.seating.plan.generator.system.util.RestResponse;

@RestController
@RequestMapping("/roles")
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/ById/{id}", method = RequestMethod.GET, produces = "application/json")
	public RestResponse findById(@PathVariable("id") Integer id) {
		try {
			logger.info("find all roles");
			Role role = roleService.findById(id);
			if (role != null) {
				logger.info("find all roles by id  successfully");
				return RestResponse.build().withSuccess().withData(role);
			} else {
				return RestResponse.build().withError("no data found");
			}
		} catch (Exception e) {
			logger.error("Failed to create institute due to:{}", e.toString(), e);
			return RestResponse.build().withError("No role create due to:" + e.getMessage());
		}
	}

	@RequestMapping(value = "/ByName/{name}", method = RequestMethod.GET, produces = "application/json")
	public RestResponse findByName(@PathVariable("name") String name) {
		try {
			logger.info("find all roles");
			Role role = roleService.findByName(name);
			if (role != null) {
				logger.info("find roles by name successfully");
				return RestResponse.build().withSuccess().withData(role);
			} else {
				return RestResponse.build().withError("no data found");
			}
		} catch (Exception e) {
			logger.error("Failed to retived roles due to:{}", e.toString(), e);
			return RestResponse.build().withError("No role create due to:" + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public RestResponse findAll() {
		try {
			logger.info("find all roles");
			List<Role> role = roleService.findAll();
			if (role != null) {
				logger.info("find all roles successfully");
				return RestResponse.build().withSuccess().withData(role);
			} else {
				return RestResponse.build().withError("no data found");
			}
		} catch (Exception e) {
			logger.error("Failed to create institute due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to create role due to:" + e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public RestResponse delete(@PathVariable("id") Integer id) {
		try {
			logger.info("delete userinstitue by id:{}", id);
			int count = roleService.delete(id);
			if (count > 0) {
				return RestResponse.build().withSuccess("role deleted successfully.");
			} else {
				return RestResponse.build().withSuccess("role not deleted successfully");
			}

		} catch (Exception e) {
			logger.error("Failed to delete role due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to delete role due to:" + e.getMessage());
		}
	}
}
