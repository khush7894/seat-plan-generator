package com.seating.plan.generator.system.req.web.view;

import com.seating.plan.generator.system.entity.LkpDepartment;

public class DepartmentEmployeeReqView {
	private int id;
	private int userId;
	private LkpDepartment lkpDepartment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LkpDepartment getLkpDepartment() {
		return lkpDepartment;
	}

	public void setLkpDepartment(LkpDepartment lkpDepartment) {
		this.lkpDepartment = lkpDepartment;
	}

}
