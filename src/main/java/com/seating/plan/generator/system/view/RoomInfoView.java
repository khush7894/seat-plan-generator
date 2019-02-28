package com.seating.plan.generator.system.view;

import java.util.Date;

public class RoomInfoView {

	private int id;
	private String roomName;
	private String halfQuantity;
	private String fullQuantity;
	private Date createdDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getHalfQuantity() {
		return halfQuantity;
	}
	public void setHalfQuantity(String halfQuantity) {
		this.halfQuantity = halfQuantity;
	}
	public String getFullQuantity() {
		return fullQuantity;
	}
	public void setFullQuantity(String fullQuantity) {
		this.fullQuantity = fullQuantity;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
}
