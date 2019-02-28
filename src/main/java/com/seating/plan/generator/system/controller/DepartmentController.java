
package com.seating.plan.generator.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seating.plan.generator.system.req.web.view.DepartmentReqView;
import com.seating.plan.generator.system.service.DepartmentService;
import com.seating.plan.generator.system.util.RestResponse;
import com.seating.plan.generator.system.view.DepartmentRespView;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public RestResponse save(@RequestBody DepartmentReqView departmentReqView) {

		try {
			logger.info("Add department in institute: {}", departmentReqView.getInstituteId());
			String message = departmentService.save(departmentReqView);
			return RestResponse.build().withSuccess(message);

		} catch (Exception e) {
			logger.error("Failed to create Department...", e.toString(), e);
			return RestResponse.build().withError("Failed to create Department..." + e.getMessage());
		}
	}

//	// find department by institute-id
//	@RequestMapping(value = "/byInstitute/{instituteId}", method = RequestMethod.GET, produces = "application/json")
//	public RestResponse findByInstituteId(@PathVariable("instituteId") Integer instituteId) {
//		try {
//			logger.info("find department by id:{}"); // done by amstech reseller
//			List<DepartmentRespView> departmentRespViewList = departmentService.findByInstituteId(instituteId);
//			if (departmentRespViewList != null) {
//
//				logger.info("Department fetched Successfully by Institute Id");
//				return RestResponse.build().withSuccess().withData(departmentRespViewList);
//			} else {
//				return RestResponse.build().withError("No data found");
//
//			}
//
//		} catch (Exception e) {
//			logger.error("Failed to find department due to:{}", e.toString(), e);
//			return RestResponse.build().withError("Failed to find department due to:" + e.getMessage());
//		}
//	}

	@RequestMapping(value = "/byInstitute", method = RequestMethod.GET)
	public RestResponse findByInstituteId() {
		try {
			Integer instituteId = 1;
			logger.info("find department by id:{}"); // done by amstech reseller
			List<DepartmentRespView> departmentRespViewList = departmentService.findByInstituteId(instituteId);
			if (departmentRespViewList != null) {

				logger.info("Department fetched successfully");
				return RestResponse.build().withSuccess().withData(departmentRespViewList);
			} else {
				return RestResponse.build().withError("No data found");

			}

		} catch (Exception e) {
			logger.error("Failed to find department due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find department due to :" + e.getMessage());
		}

	}

	// mark Deleted....
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public RestResponse delete(@PathVariable("id") Integer id) {
		try {
			logger.info("Delete Department for Id:{}", id);
			int rowCount = departmentService.delete(id);
			if (rowCount > 0) {
				return RestResponse.build().withSuccess("Department deleted successfully.");
			} else {
				return RestResponse.build().withError("No record deleted.");
			}
		} catch (Exception e) {
			logger.error("Failed to delete Department due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to delete Department due to :" + e.getMessage());
		}

	}

}
