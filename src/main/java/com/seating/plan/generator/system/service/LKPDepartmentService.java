package com.seating.plan.generator.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seating.plan.generator.system.entity.LkpDepartment;
import com.seating.plan.generator.system.entity.LkpSemester;
import com.seating.plan.generator.system.repo.LkpDepartmentRepo;
import com.seating.plan.generator.system.repo.LkpSemesterRepo;
import com.seating.plan.generator.system.view.LKPSemesterRespView;

@Service
@Transactional
public class LKPDepartmentService {
	private static final Logger logger = LoggerFactory.getLogger(LKPDepartmentService.class);

	@Autowired
	private LkpDepartmentRepo lkpDepartmentRepo;

	public List<LkpDepartment> findAll() {

		return lkpDepartmentRepo.findAll();
		
	}

}
