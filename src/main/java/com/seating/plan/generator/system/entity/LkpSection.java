package com.seating.plan.generator.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lkp_section database table.
 * 
 */
@Entity
@Table(name="lkp_section")
@NamedQuery(name="LkpSection.findAll", query="SELECT l FROM LkpSection l")
public class LkpSection implements Serializable {
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
	@OneToMany(mappedBy="lkpSection")
	private List<BatchSectionStudent> batchSectionStudents;

	public LkpSection() {
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
		batchSectionStudent.setLkpSection(this);

		return batchSectionStudent;
	}

	public BatchSectionStudent removeBatchSectionStudent(BatchSectionStudent batchSectionStudent) {
		getBatchSectionStudents().remove(batchSectionStudent);
		batchSectionStudent.setLkpSection(null);

		return batchSectionStudent;
	}

}