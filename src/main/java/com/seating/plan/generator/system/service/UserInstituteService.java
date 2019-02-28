package com.seating.plan.generator.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seating.plan.generator.system.entity.UserInstitute;
import com.seating.plan.generator.system.repo.UserInstituteRepo;

@Transactional
@Service
public class UserInstituteService {

	@Autowired
	private UserInstituteRepo userInstituteRepo;

	public void save() {

	}

	public UserInstitute findById(Integer id) {
		return userInstituteRepo.findById(id);
	}

	public List<UserInstitute> findByUserId(Integer id) {
		return userInstituteRepo.findByUserId(id);
	}

	public List<UserInstitute> findAll() {
		return userInstituteRepo.findAll();
	}

	public int markDeleted(Integer id) {
		return userInstituteRepo.markDeleted(id);
	}
}
