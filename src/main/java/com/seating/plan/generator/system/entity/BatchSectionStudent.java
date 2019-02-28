package com.seating.plan.generator.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the batch_section_student database table.
 * 
 */
@Entity
@Table(name="batch_section_student")
@NamedQuery(name="BatchSectionStudent.findAll", query="SELECT b FROM BatchSectionStudent b")
public class BatchSectionStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="is_deleted")
	private int isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;

	//bi-directional many-to-one association to Batch
	@ManyToOne
	private Batch batch;

	//bi-directional many-to-one association to LkpSection
	@ManyToOne
	@JoinColumn(name="lkp_section_id")
	private LkpSection lkpSection;

	//bi-directional many-to-one association to LkpSemester
	@ManyToOne
	@JoinColumn(name="lkp_semester_id")
	private LkpSemester lkpSemester;

	//bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	public BatchSectionStudent() {
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

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Batch getBatch() {
		return this.batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public LkpSection getLkpSection() {
		return this.lkpSection;
	}

	public void setLkpSection(LkpSection lkpSection) {
		this.lkpSection = lkpSection;
	}

	public LkpSemester getLkpSemester() {
		return this.lkpSemester;
	}

	public void setLkpSemester(LkpSemester lkpSemester) {
		this.lkpSemester = lkpSemester;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}