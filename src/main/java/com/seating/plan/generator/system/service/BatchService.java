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
import com.seating.plan.generator.system.repo.BatchRepo;
import com.seating.plan.generator.system.repo.CourseRepo;
import com.seating.plan.generator.system.req.web.view.BatchReqView;
import com.seating.plan.generator.system.util.DateHelper;
import com.seating.plan.generator.system.view.BatchRespView;

@Transactional
@Service
public class BatchService {

	private static final Logger logger = LoggerFactory.getLogger(BatchService.class);

	@Autowired(required = true)
	private BatchRepo batchRepo;

	@Autowired
	private CourseRepo courseRepo;

	public Batch save(BatchReqView batchReqView) throws Exception {
		logger.info("Fetching entity for permanent course-id:{} ", batchReqView.getCourseId());
		Course course = courseRepo.findOne(batchReqView.getCourseId());
		logger.info("Fetched entity for permanent course-id:{}", batchReqView.getCourseId());

		Batch batch = new Batch();

		batch.setCourse(course);
		batch.setName(batchReqView.getName());
		batch.setCode(batchReqView.getCode());
		batch.setDescription(batchReqView.getDescription());
		batch.setStartYear(batchReqView.getStartYear());
		batch.setEndYear(batchReqView.getEndeYear());
		batch.setCreatedDate(DateHelper.getCurrentDate());
		batch.setIsDeleted(0);

		return batchRepo.save(batch);
	}

	public String update(BatchReqView batchReqView) throws Exception {
		logger.info("Fetching entity for permanent course-id:{} ", batchReqView.getCourseId());
		Course course = courseRepo.findOne(batchReqView.getCourseId());
		logger.info("Fetched entity for permanent course-id:{}", batchReqView.getCourseId());

		Batch ref = batchRepo.findOne(batchReqView.getId());

		logger.info("Fetching batch id:{}", ref.getId());
		Batch batch = new Batch();
		batch.setId(ref.getId());
		batch.setCourse(course);
		batch.setName(batchReqView.getName());
		batch.setCode(batchReqView.getCode());
		batch.setDescription(batchReqView.getDescription());
		batch.setStartYear(batchReqView.getStartYear());
		batch.setEndYear(batchReqView.getEndeYear());
		batch.setCreatedDate(DateHelper.getCurrentDate());
		batch.setIsDeleted(0);

		Batch updateBatch = batchRepo.save(batch);
		if (updateBatch != null) {
			return "Batch Updated Successfully..";
		}
		return "Failed to update Batch!!!";

	}

	public List<BatchRespView> findByCourseId(Integer instituteId, Integer courseId) {
		List<Batch> batchList = batchRepo.findByCourseId(instituteId, courseId);

		List<BatchRespView> batchRespViewList = new ArrayList<>();
		BatchRespView batchRespView = null;

		for (Batch batch : batchList) {
			batchRespView = new BatchRespView();
			batchRespView.setId(batch.getId());
			batchRespView.setCode(batch.getCode());
			batchRespView.setName(batch.getName());
			batchRespView.setDescription(batch.getDescription());
			batchRespView.setStartYear(batch.getStartYear());
			batchRespView.setEndeYear(batch.getEndYear());
			batchRespView.setCourseId(batch.getCourse().getId());
			batchRespView.setCourseName(batch.getCourse().getLkpCourse().getName());
			batchRespViewList.add(batchRespView);
		}
		return batchRespViewList;

	}

	public int markDeleted(Integer id) {

		return batchRepo.markDeleted(id);
	}

}
