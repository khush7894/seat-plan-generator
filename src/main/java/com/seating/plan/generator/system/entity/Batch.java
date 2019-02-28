package com.seating.plan.generator.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the batch database table.
 * 
 */
@Entity
@Table(name="batch")
@NamedQuery(name="Batch.findAll", query="SELECT b FROM Batch b")
public class Batch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_year")
	private Date endYear;

	@Column(name="is_deleted")
	private int isDeleted;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_year")
	private Date startYear;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;

	//bi-directional many-to-one association to Course
	@ManyToOne
	private Course course;

	//bi-directional many-to-one association to BatchSectionStudent
	@OneToMany(mappedBy="batch")
	private List<BatchSectionStudent> batchSectionStudents;

	public Batch() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndYear() {
		return this.endYear;
	}

	public void setEndYear(Date endYear) {
		this.endYear = endYear;
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

	public Date getStartYear() {
		return this.startYear;
	}

	public void setStartYear(Date startYear) {
		this.startYear = startYear;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<BatchSectionStudent> getBatchSectionStudents() {
		return this.batchSectionStudents;
	}

	public void setBatchSectionStudents(List<BatchSectionStudent> batchSectionStudents) {
		this.batchSectionStudents = batchSectionStudents;
	}

	public BatchSectionStudent addBatchSectionStudent(BatchSectionStudent batchSectionStudent) {
		getBatchSectionStudents().add(batchSectionStudent);
		batchSectionStudent.setBatch(this);

		return batchSectionStudent;
	}

	public BatchSectionStudent removeBatchSectionStudent(BatchSectionStudent batchSectionStudent) {
		getBatchSectionStudents().remove(batchSectionStudent);
		batchSectionStudent.setBatch(null);

		return batchSectionStudent;
	}

}