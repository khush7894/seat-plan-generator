package com.seating.plan.generator.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seating.plan.generator.system.req.web.view.BatchReqView;
import com.seating.plan.generator.system.req.web.view.SubjectReqView;
import com.seating.plan.generator.system.service.BatchService;
import com.seating.plan.generator.system.service.SubjectService;
import com.seating.plan.generator.system.util.RestResponse;
import com.seating.plan.generator.system.view.BatchRespView;
import com.seating.plan.generator.system.view.SubjectRespView;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

	private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

	@Autowired
	private SubjectService subjectService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public RestResponse save(@RequestBody SubjectReqView subjectReqView) {
		try {
			logger.info("save Subject:{}", subjectReqView);
			subjectService.save(subjectReqView);
		} catch (Exception e) {
			logger.error("Failed to add subject due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to add subject due to :" + e.getMessage());
		}
		logger.error("Subject added successfully.");
		return RestResponse.build().withSuccess("Subject added successfully.");
	}

//	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
//	public RestResponse update(@RequestBody SubjectReqView subjectReqView) {
//		try {
//			logger.info("Update Batch:{}", subjectReqView);
//			subjectService.update(subjectReqView);
//		} catch (Exception e) {
//			logger.error("Failed to Update batch due to:{}", e.toString(), e);
//			return RestResponse.build().withError("Failed to update batch due to :" + e.getMessage());
//		}
//		logger.error("Batch updated successfully.");
//		return RestResponse.build().withSuccess("batch updated successfully.");
//
//	}

	// find by course id
	@RequestMapping(value = "/byFilter", method = RequestMethod.GET)
	public RestResponse findById(@RequestParam("courseId") Integer courseId,@RequestParam("lkpSemesterId") Integer lkpSemesterId) {
		try {
			Integer instituteId = 1;
			logger.info("find subject for courseId:{}, lkpSemesterId:{}", courseId, lkpSemesterId);
			List<SubjectRespView> subjectRespviewList = subjectService.findByCourseIdSemesterId(lkpSemesterId, courseId);
			if (subjectRespviewList != null) {
				return RestResponse.build().withSuccess().withData(subjectRespviewList);
			} else {
				return RestResponse.build().withError("No batch data found");
			}

		} catch (Exception e) {
			logger.error("Failed to find subject due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find subject by courseId semesterId due to :" + e.getMessage());
		}

	}
	//
	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces
	// = "application/json")
	// public RestResponse delete(@PathVariable("id") Integer id) {
	// try {
	// logger.info("Batch delete by BatchId: {}", id);
	// int userDelete = subjectService.markDeleted(id);
	// if (userDelete > 0) {
	// return RestResponse.build().withSuccess("Batch Deleted SuccessFully");
	// } else {
	// return RestResponse.build().withError("No Batch data deleted");
	// }
	// } catch (Exception e) {
	// logger.error("Failed to delete Batch due to {}:", e.toString(), e);
	// return RestResponse.build().withError("Failed to delete Batch due to :" +
	// e.getMessage());
	// }
	// }

}
