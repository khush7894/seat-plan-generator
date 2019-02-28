package com.seating.plan.generator.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sem_exam_info database table.
 * 
 */
@Entity
@Table(name="sem_exam_info")
@NamedQuery(name="SemExamInfo.findAll", query="SELECT s FROM SemExamInfo s")
public class SemExamInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="exam_name_c_40")
	private String examNameC40;

	@Column(name="exctrl_c_6")
	private String exctrlC6;

	@Column(name="is_deleted")
	private int isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;

	public SemExamInfo() {
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

	public String getExamNameC40() {
		return this.examNameC40;
	}

	public void setExamNameC40(String examNameC40) {
		this.examNameC40 = examNameC40;
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

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}