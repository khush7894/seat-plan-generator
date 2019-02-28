package com.seating.plan.generator.system.req.web.view;

import java.util.Date;

public class BatchReqView {

	private int id;
	private String name;
	private String code;
	private String description;
	private Date startYear;
	private Date endeYear;
	private int courseId;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartYear() {
		return startYear;
	}

	public void setStartYear(Date startYear) {
		this.startYear = startYear;
	}

	public Date getEndeYear() {
		return endeYear;
	}

	public void setEndeYear(Date endeYear) {
		this.endeYear = endeYear;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

}
