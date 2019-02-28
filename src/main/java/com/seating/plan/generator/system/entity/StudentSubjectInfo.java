package com.seating.plan.generator.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the student_subject_info database table.
 * 
 */
@Entity
@Table(name="student_subject_info")
@NamedQuery(name="StudentSubjectInfo.findAll", query="SELECT s FROM StudentSubjectInfo s")
public class StudentSubjectInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="act_subject_c_8")
	private String actSubjectC8;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="det_c_1")
	private String detC1;

	@Column(name="enrollment_c_15")
	private String enrollmentC15;

	@Column(name="exctrl_c_6")
	private String exctrlC6;

	@Column(name="is_deleted")
	private int isDeleted;

	@Column(name="roll_number")
	private String rollNumber;

	@Column(name="subject_code_c_2")
	private String subjectCodeC2;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;

	public StudentSubjectInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActSubjectC8() {
		return this.actSubjectC8;
	}

	public void setActSubjectC8(String actSubjectC8) {
		this.actSubjectC8 = actSubjectC8;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDetC1() {
		return this.detC1;
	}

	public void setDetC1(String detC1) {
		this.detC1 = detC1;
	}

	public String getEnrollmentC15() {
		return this.enrollmentC15;
	}

	public void setEnrollmentC15(String enrollmentC15) {
		this.enrollmentC15 = enrollmentC15;
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

	public String getRollNumber() {
		return this.rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getSubjectCodeC2() {
		return this.subjectCodeC2;
	}

	public void setSubjectCodeC2(String subjectCodeC2) {
		this.subjectCodeC2 = subjectCodeC2;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}