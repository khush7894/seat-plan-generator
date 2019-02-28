package com.seating.plan.generator.system.view;

import java.util.Date;
import java.util.List;

public class SeatingPlanRawDataView {

	private String subjectCode;
	private String subjectName;
	private String shift;
	private String examName;
	private List<String> rollNumberList;

	public List<String> getRollNumberList() {
		return rollNumberList;
	}

	public void setRollNumberList(List<String> rollNumberList) {
		this.rollNumberList = rollNumberList;
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

}
