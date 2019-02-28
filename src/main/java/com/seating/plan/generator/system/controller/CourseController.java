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

import com.seating.plan.generator.system.entity.Course;
import com.seating.plan.generator.system.req.web.view.CourseReqView;
import com.seating.plan.generator.system.service.CourseService;
import com.seating.plan.generator.system.util.RestResponse;
import com.seating.plan.generator.system.view.CourseRespView;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;



	// Save
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public RestResponse save(@RequestBody CourseReqView courseReqView) {

		try {
			logger.info("Add course in department: {}", courseReqView.getDepartmentId());
			String message = courseService.save(courseReqView);
			return RestResponse.build().withSuccess(message);

		} catch (Exception e) {
			logger.error("Failed to create course...", e.toString(), e);
			return RestResponse.build().withError("Failed to create Department..." + e.getMessage());
		}

	}

	// find by Department id
	@RequestMapping(value = "/byDepartmentId/{departmentId}", method = RequestMethod.GET, produces = "application/json")
	public RestResponse findById(@PathVariable("departmentId") Integer departmentId) {
		try {
			Integer instituteId = 1;

			logger.info("find Course for departmentId:{}, instituteId:{}", departmentId, instituteId);
			List<Course> coursesList  = courseService.findByDepartment(instituteId, departmentId);
			if (coursesList != null) {
				logger.info("Succesfully course retrived by departmentId:{}", coursesList);
				return RestResponse.build().withSuccess().withData(coursesList);
			} else {
				return RestResponse.build().withError("No course data found");
			}

		} catch (Exception e) {
			logger.error("Failed to find course due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find course by departmentId due to :" + e.getMessage());
		}

	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public RestResponse delete(@PathVariable("id") Integer id) {
		try {
			logger.info("delete Course for Id:{}", id);
			int rowCount = courseService.delete(id);
			if (rowCount > 0) {
				return RestResponse.build().withSuccess("Course deleted successfully.");
			} else {
				return RestResponse.build().withError("No Course record deleted.");
			}
		} catch (Exception e) {
			logger.error("Failed to delete Course due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to delete Course due to :" + e.getMessage());
		}

	}
}
