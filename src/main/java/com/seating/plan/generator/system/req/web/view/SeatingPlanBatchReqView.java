package com.seating.plan.generator.system.req.web.view;

import java.util.List;

public class SeatingPlanBatchReqView {

	// private int batchId;
	private int id;
	private String semesterName;
	private int courseId;
	private String courseName;
	private int departmentId;
	private String departmentName;
	private String subjectCode;
	private String subjectName;
	private List<String> rollNumber;
	private String shift;
	private String examName;
	private int totalStudent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<String> getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(List<String> rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public int getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}

	@Override
	public String toString() {
		return "SeatingPlanBatchReqView [semesterName=" + semesterName + ", courseId=" + courseId + ", courseName=" + courseName + ", departmentId=" + departmentId + ", departmentName="
				+ departmentName + ", subjectCode=" + subjectCode + ", subjectName=" + subjectName + "]";
	}

}