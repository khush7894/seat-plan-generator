package com.seating.plan.generator.system.req.web.view;

import java.util.Date;
import java.util.List;

public class SeatingPlanGenerateReqView {

	private List<SeatingPlanBatchReqView> batchInfoViewArray;
	private List<String> roomNameArray;
	private List<Integer> studentCountArray;
	private Date examDate;
	private String examDetail;

	public List<SeatingPlanBatchReqView> getBatchInfoViewArray() {
		return batchInfoViewArray;
	}

	public void setBatchInfoViewArray(List<SeatingPlanBatchReqView> batchInfoViewArray) {
		this.batchInfoViewArray = batchInfoViewArray;
	}

	public List<String> getRoomNameArray() {
		return roomNameArray;
	}

	public void setRoomNameArray(List<String> roomNameArray) {
		this.roomNameArray = roomNameArray;
	}

	public List<Integer> getStudentCountArray() {
		return studentCountArray;
	}

	public void setStudentCountArray(List<Integer> studentCountArray) {
		this.studentCountArray = studentCountArray;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamDetail() {
		return examDetail;
	}

	public void setExamDetail(String examDetail) {
		this.examDetail = examDetail;
	}

	@Override
	public String toString() {
		return "SeatingPlanGenerateReqView [batchInfoViewArray=" + batchInfoViewArray.toString() + ", roomNameArray=" + roomNameArray + ", studentCountArray=" + studentCountArray + ", examDate="
				+ examDate + ", examDetail=" + examDetail + "]";
	}

}