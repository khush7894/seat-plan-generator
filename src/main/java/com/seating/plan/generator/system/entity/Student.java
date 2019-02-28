package com.seating.plan.generator.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Table(name="student")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="contact_one")
	private String contactOne;

	@Column(name="contact_two")
	private String contactTwo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private String email;

	@Column(name="enrollment_no")
	private String enrollmentNo;

	@Column(name="father_name")
	private String fatherName;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	@Column(name="is_deleted")
	private int isDeleted;

	@Column(name="last_name")
	private String lastName;

	@Column(name="mother_name")
	private String motherName;

	@Column(name="roll_number")
	private String rollNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;

	//bi-directional many-to-one association to BatchSectionStudent
	@OneToMany(mappedBy="student")
	private List<BatchSectionStudent> batchSectionStudents;

	public Student() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContactOne() {
		return this.contactOne;
	}

	public void setContactOne(String contactOne) {
		this.contactOne = contactOne;
	}

	public String getContactTwo() {
		return this.contactTwo;
	}

	public void setContactTwo(String contactTwo) {
		this.contactTwo = contactTwo;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnrollmentNo() {
		return this.enrollmentNo;
	}

	public void setEnrollmentNo(String enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getRollNumber() {
		return this.rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<BatchSectionStudent> getBatchSectionStudents() {
		return this.batchSectionStudents;
	}

	public void setBatchSectionStudents(List<BatchSectionStudent> batchSectionStudents) {
		this.batchSectionStudents = batchSectionStudents;
	}

	public BatchSectionStudent addBatchSectionStudent(BatchSectionStudent batchSectionStudent) {
		getBatchSectionStudents().add(batchSectionStudent);
		batchSectionStudent.setStudent(this);

		return batchSectionStudent;
	}

	public BatchSectionStudent removeBatchSectionStudent(BatchSectionStudent batchSectionStudent) {
		getBatchSectionStudents().remove(batchSectionStudent);
		batchSectionStudent.setStudent(null);

		return batchSectionStudent;
	}

}