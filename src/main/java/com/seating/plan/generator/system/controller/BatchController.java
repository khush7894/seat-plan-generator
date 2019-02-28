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

import com.seating.plan.generator.system.req.web.view.BatchReqView;
import com.seating.plan.generator.system.service.BatchService;
import com.seating.plan.generator.system.util.RestResponse;
import com.seating.plan.generator.system.view.BatchRespView;

@RestController
@RequestMapping("/batches")
public class BatchController {

	private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

	@Autowired
	private BatchService batchService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public RestResponse save(@RequestBody BatchReqView batchReqView) {
		try {
			logger.info("save Batch:{}", batchReqView);
			batchService.save(batchReqView);
		} catch (Exception e) {
			logger.error("Failed to add batch due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to add department due to :" + e.getMessage());
		}
		logger.error("Batch added successfully.");
		return RestResponse.build().withSuccess("batch added successfully.");
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public RestResponse update(@RequestBody BatchReqView batchReqView) {
		try {
			logger.info("Update Batch:{}", batchReqView);
			batchService.update(batchReqView);
		} catch (Exception e) {
			logger.error("Failed to Update batch due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to update batch due to :" + e.getMessage());
		}
		logger.error("Batch updated successfully.");
		return RestResponse.build().withSuccess("batch updated successfully.");

	}

	// find by course id
	@RequestMapping(value = "/byCourse/{courseId}", method = RequestMethod.GET)
	public RestResponse findById(@PathVariable("courseId") Integer courseId) {
		try {
			Integer instituteId = 1;
			logger.info("find batch for courseId:{}", courseId);
			List<BatchRespView> batchRespviewList = batchService.findByCourseId(instituteId, courseId);
			if (batchRespviewList != null) {
				logger.info("Succesfully batch retrived by course-Id:{}", courseId);
				return RestResponse.build().withSuccess().withData(batchRespviewList);
			} else {
				return RestResponse.build().withError("No batch data found");
			}

		} catch (Exception e) {
			logger.error("Failed to find batch due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find batch by courseId due to :" + e.getMessage());
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public RestResponse delete(@PathVariable("id") Integer id) {
		try {
			logger.info("Batch delete by BatchId: {}", id);
			int userDelete = batchService.markDeleted(id);
			if (userDelete > 0) {
				return RestResponse.build().withSuccess("Batch Deleted SuccessFully");
			} else {
				return RestResponse.build().withError("No Batch data deleted");
			}
		} catch (Exception e) {
			logger.error("Failed to delete Batch due to  {}:", e.toString(), e);
			return RestResponse.build().withError("Failed to delete Batch due to :" + e.getMessage());
		}
	}

}
