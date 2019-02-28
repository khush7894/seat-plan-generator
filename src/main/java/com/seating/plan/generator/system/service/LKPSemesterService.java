package com.seating.plan.generator.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seating.plan.generator.system.entity.LkpSemester;
import com.seating.plan.generator.system.repo.LkpSemesterRepo;
import com.seating.plan.generator.system.view.LKPSemesterRespView;

@Service
@Transactional
public class LKPSemesterService {
	private static final Logger logger = LoggerFactory.getLogger(LKPSemesterService.class);

	@Autowired
	private LkpSemesterRepo lkpSemesterRepo;

	public List<LKPSemesterRespView> findAll() {

		List<LkpSemester> lkpSemesterList = lkpSemesterRepo.findAll();
		List<LKPSemesterRespView> lkpSemesterRespViewList = new ArrayList<>();

		for (LkpSemester lkpSemester : lkpSemesterList) {
			LKPSemesterRespView lkpSemesterRespView = new LKPSemesterRespView();
			lkpSemesterRespView.setId(lkpSemester.getId());
			lkpSemesterRespView.setName(lkpSemester.getName());
			lkpSemesterRespViewList.add(lkpSemesterRespView);

		}
		return lkpSemesterRespViewList;

	}

}
