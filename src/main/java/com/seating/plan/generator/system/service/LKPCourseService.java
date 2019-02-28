package com.seating.plan.generator.system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seating.plan.generator.system.entity.LkpCourse;
import com.seating.plan.generator.system.repo.LkpCourseRepo;

@Service
@Transactional
public class LKPCourseService {
	private static final Logger logger = LoggerFactory.getLogger(LKPCourseService.class);

	@Autowired
	private LkpCourseRepo lkpCourseRepo;

	public List<LkpCourse> findAll() {

		return lkpCourseRepo.findAll();
		
	}

}
