package com.seating.plan.generator.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seating.plan.generator.system.entity.Course;
import com.seating.plan.generator.system.repo.CourseRepo;
import com.seating.plan.generator.system.repo.DepartmentRepo;
import com.seating.plan.generator.system.repo.LkpCourseRepo;
import com.seating.plan.generator.system.req.web.view.CourseReqView;
import com.seating.plan.generator.system.util.DateHelper;
import com.seating.plan.generator.system.view.CourseRespView;

@Transactional
@Service
public class CourseService {

	@Autowired(required = true)
	private CourseRepo courseRepo;

	@Autowired(required = true)
	private DepartmentRepo departmentRepo;

	@Autowired
	private LkpCourseRepo lkpCourseRepo;

	// Save

	public String save(CourseReqView courseReqView) throws Exception {

		Course course = new Course();

		course.setDepartment(departmentRepo.findOne(courseReqView.getDepartmentId()));
		course.setLkpCourse(lkpCourseRepo.findOne(courseReqView.getLkpCouseId()));
		course.setCreatedDate(DateHelper.getCurrentDate());
		course.setIsDeleted(0);

		Course saveCourse = courseRepo.save(course);

		if (saveCourse != null) {
			return "Course successfully Saved..";
		}
		return "Failed to save Course.";
	}

	public List<Course> findByDepartment(Integer instituteId, Integer departmentId) {
		List<Course> courseList = courseRepo.findByDepartment(instituteId, departmentId);

		return courseList;

	}

	public int delete(Integer id) {
		return courseRepo.markDeleted(id);
	}
}
