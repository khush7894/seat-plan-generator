package com.seating.plan.generator.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seating.plan.generator.system.entity.Role;
import com.seating.plan.generator.system.repo.RoleRepo;

@Transactional
@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;

	public Role findById(Integer id) {
		return roleRepo.findById(id);
	}

	public Role findByName(String name) {
		return roleRepo.findByName(name);
	}

	public List<Role> findAll() {
		return roleRepo.findAll();
	}

	public int delete(Integer id) {
		return roleRepo.markDeleted(id);
	}
}
