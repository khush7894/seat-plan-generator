package com.seating.plan.generator.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seating.plan.generator.system.entity.Batch;
import com.seating.plan.generator.system.entity.Course;
import com.seating.plan.generator.system.entity.LkpSemester;
import com.seating.plan.generator.system.entity.Subject;
import com.seating.plan.generator.system.repo.BatchRepo;
import com.seating.plan.generator.system.repo.CourseRepo;
import com.seating.plan.generator.system.repo.LkpSemesterRepo;
import com.seating.plan.generator.system.repo.SubjectRepo;
import com.seating.plan.generator.system.req.web.view.BatchReqView;
import com.seating.plan.generator.system.req.web.view.SubjectReqView;
import com.seating.plan.generator.system.util.DateHelper;
import com.seating.plan.generator.system.view.BatchRespView;
import com.seating.plan.generator.system.view.SubjectRespView;

@Transactional
@Service
public class SubjectService {

	private static final Logger logger = LoggerFactory.getLogger(SubjectService.class);

	@Autowired(required = true)
	private BatchRepo batchRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private LkpSemesterRepo lkpSemesterRepo;

	@Autowired
	private SubjectRepo subjectRepo;

	public Subject save(SubjectReqView subjectReqView) throws Exception {
		logger.info("Fetching entity for permanent course-id:{} ", subjectReqView.getCourseId());
		Course course = courseRepo.findOne(subjectReqView.getCourseId());
		logger.info("Fetched entity for permanent course-id:{}", subjectReqView.getCourseId());

		logger.info("Fetching entity for permanent lkp-semester-id:{} ", subjectReqView.getLkpSemesterId());
		LkpSemester lkpSemester = lkpSemesterRepo.findOne(subjectReqView.getLkpSemesterId());
		logger.info("Fetched entity for permanent lkp-semester-id:{}", subjectReqView.getLkpSemesterId());

		Subject subject = new Subject();

		subject.setCourse(course);
		subject.setLkpSemester(lkpSemester);
		subject.setName(subjectReqView.getName());
		subject.setCode(subjectReqView.getCode());
		subject.setCreatedDate(DateHelper.getCurrentDate());

		return subjectRepo.save(subject);
	}

	public List<SubjectRespView> findByCourseIdSemesterId(Integer lkpSemesterId, Integer courseId) {
		List<Subject> semesterList = subjectRepo.findByCourseIdSemesterId(lkpSemesterId, courseId);

		List<SubjectRespView> subjectRespViewList = new ArrayList<>();
		SubjectRespView subjectRespView = null;

		for (Subject subject : semesterList) {
			subjectRespView = new SubjectRespView();
			subjectRespView.setId(subject.getId());
			subjectRespView.setCode(subject.getCode());
			subjectRespView.setName(subject.getName());
			subjectRespView.setCourseId(subject.getCourse().getId());
			subjectRespView.setCourseName(subject.getCourse().getLkpCourse().getName());
			subjectRespView.setDepartmentId(subject.getCourse().getDepartment().getId());
			subjectRespView.setDepartmentName(subject.getCourse().getDepartment().getLkpDepartment().getName());
			subjectRespView.setSemesterName(subject.getLkpSemester().getName());
			subjectRespViewList.add(subjectRespView);
		}
		return subjectRespViewList;

	}

	public int markDeleted(Integer id) {

		return batchRepo.markDeleted(id);
	}

}
