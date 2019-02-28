package com.seating.plan.generator.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seating.plan.generator.system.entity.Department;
import com.seating.plan.generator.system.repo.DepartmentRepo;
import com.seating.plan.generator.system.repo.InstituteRepo;
import com.seating.plan.generator.system.repo.LkpDepartmentRepo;
import com.seating.plan.generator.system.req.web.view.DepartmentReqView;
import com.seating.plan.generator.system.util.DateHelper;
import com.seating.plan.generator.system.view.DepartmentRespView;

@Transactional
@Service
public class DepartmentService {

	@Autowired(required = true)
	private DepartmentRepo departmentRepo;

	@Autowired
	private InstituteRepo instituteRepo;

	@Autowired
	private LkpDepartmentRepo lkpDepartmentRepo;

	public String save(DepartmentReqView departmentReqView) throws Exception {

		Department department = new Department();

		department.setInstitute(instituteRepo.findOne(departmentReqView.getInstituteId()));
		department.setLkpDepartment(lkpDepartmentRepo.findOne(departmentReqView.getLkpDepartmentId()));
		department.setCreatedDate(DateHelper.getCurrentDate());
		department.setIsDeleted(0);

		Department saveDepartment = departmentRepo.save(department);

		if (saveDepartment != null) {
			return "Department Created Sucessfully";
		}

		return "Failed to create department.";
	}

	public List<DepartmentRespView> findByInstituteId(Integer instituteId) {

		List<Department> departmentList= departmentRepo.findByInstituteId(instituteId);

		List<DepartmentRespView> departmentRespViewList= new ArrayList<>();
		DepartmentRespView departmentRespView=null;

		for (Department department: departmentList) {
			departmentRespView = new DepartmentRespView();
			departmentRespView.setId(department.getId());
			departmentRespView.setInstituteId(department.getInstitute().getId());
			departmentRespView.setInstituteName(department.getInstitute().getName());
			departmentRespView.setName(department.getLkpDepartment().getName());
			departmentRespView.setCode(department.getLkpDepartment().getCode());

			departmentRespViewList.add(departmentRespView);

		}
		return departmentRespViewList;
	}

	public DepartmentRespView findById(Integer id) {

		Department department= departmentRepo.findById(id);

		DepartmentRespView departmentRespView = new DepartmentRespView();

		departmentRespView = new DepartmentRespView();
		departmentRespView.setId(department.getLkpDepartment().getId());
		departmentRespView.setInstituteId(department.getInstitute().getId());
		departmentRespView.setInstituteName(department.getInstitute().getName());
		departmentRespView.setName(department.getLkpDepartment().getName());

		return departmentRespView;
	}

	public int delete(Integer id) {
		return departmentRepo.markDeleted(id);
	}

}
