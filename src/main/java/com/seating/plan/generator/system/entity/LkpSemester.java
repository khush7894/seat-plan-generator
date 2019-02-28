package com.seating.plan.generator.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lkp_semester database table.
 * 
 */
@Entity
@Table(name="lkp_semester")
@NamedQuery(name="LkpSemester.findAll", query="SELECT l FROM LkpSemester l")
public class LkpSemester implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="is_deleted")
	private int isDeleted;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;

	//bi-directional many-to-one association to BatchSectionStudent
	@OneToMany(mappedBy="lkpSemester")
	private List<BatchSectionStudent> batchSectionStudents;

	//bi-directional many-to-one association to Subject
	@OneToMany(mappedBy="lkpSemester")
	private List<Subject> subjects;

	public LkpSemester() {
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

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
		batchSectionStudent.setLkpSemester(this);

		return batchSectionStudent;
	}

	public BatchSectionStudent removeBatchSectionStudent(BatchSectionStudent batchSectionStudent) {
		getBatchSectionStudents().remove(batchSectionStudent);
		batchSectionStudent.setLkpSemester(null);

		return batchSectionStudent;
	}

	public List<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Subject addSubject(Subject subject) {
		getSubjects().add(subject);
		subject.setLkpSemester(this);

		return subject;
	}

	public Subject removeSubject(Subject subject) {
		getSubjects().remove(subject);
		subject.setLkpSemester(null);

		return subject;
	}

}