package com.seating.plan.generator.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the student_info database table.
 * 
 */
@Entity
@Table(name="subject_info")
@NamedQuery(name="SubjectInfo.findAll", query="SELECT s FROM SubjectInfo s")
public class SubjectInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="actsubcode_c_7")
	private String actsubcodeC7;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="exctrl_c_6")
	private String exctrlC6;

	@Column(name="is_deleted")
	private int isDeleted;

	@Column(name="subcode_c_2")
	private String subcodeC2;

	@Column(name="subdes1_c_35")
	private String subdes1C35;

	@Column(name="subdess2_c_20")
	private String subdess2C20;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;

	public SubjectInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActsubcodeC7() {
		return this.actsubcodeC7;
	}

	public void setActsubcodeC7(String actsubcodeC7) {
		this.actsubcodeC7 = actsubcodeC7;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getExctrlC6() {
		return this.exctrlC6;
	}

	public void setExctrlC6(String exctrlC6) {
		this.exctrlC6 = exctrlC6;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getSubcodeC2() {
		return this.subcodeC2;
	}

	public void setSubcodeC2(String string) {
		this.subcodeC2 = string;
	}

	public String getSubdes1C35() {
		return this.subdes1C35;
	}

	public void setSubdes1C35(String subdes1C35) {
		this.subdes1C35 = subdes1C35;
	}

	public String getSubdess2C20() {
		return this.subdess2C20;
	}

	public void setSubdess2C20(String subdess2C20) {
		this.subdess2C20 = subdess2C20;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}