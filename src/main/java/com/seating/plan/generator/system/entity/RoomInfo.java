package com.seating.plan.generator.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the room_info database table.
 * 
 */
@Entity
@Table(name="room_info")
@NamedQuery(name="RoomInfo.findAll", query="SELECT r FROM RoomInfo r")
public class RoomInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="full_quantity")
	private String fullQuantity;

	@Column(name="half_quantity")
	private String halfQuantity;

	@Column(name="is_deleted")
	private int isDeleted;

	@Column(name="room_name")
	private String roomName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;

	public RoomInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getFullQuantity() {
		return this.fullQuantity;
	}

	public void setFullQuantity(String fullQuantity) {
		this.fullQuantity = fullQuantity;
	}

	public String getHalfQuantity() {
		return this.halfQuantity;
	}

	public void setHalfQuantity(String halfQuantity) {
		this.halfQuantity = halfQuantity;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}