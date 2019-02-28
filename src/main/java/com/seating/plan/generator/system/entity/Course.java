package com.seating.plan.generator.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the course database table.
 * 
 */
@Entity
@Table(name="course")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
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
	@OneToMany(mappedBy="course")
	private List<Batch> batches;

	//bi-directional many-to-one association to Department
	@ManyToOne
	private Department department;

	//bi-directional many-to-one association to LkpCourse
	@ManyToOne
	@JoinColumn(name="lkp_course_id")
	private LkpCourse lkpCourse;

	//bi-directional many-to-one association to Subject
	@OneToMany(mappedBy="course")
	private List<Subject> subjects;

	public Course() {
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

	public List<Batch> getBatches() {
		return this.batches;
	}

	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}

	public Batch addBatch(Batch batch) {
		getBatches().add(batch);
		batch.setCourse(this);

		return batch;
	}

	public Batch removeBatch(Batch batch) {
		getBatches().remove(batch);
		batch.setCourse(null);

		return batch;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public LkpCourse getLkpCourse() {
		return this.lkpCourse;
	}

	public void setLkpCourse(LkpCourse lkpCourse) {
		this.lkpCourse = lkpCourse;
	}

	public List<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Subject addSubject(Subject subject) {
		getSubjects().add(subject);
		subject.setCourse(this);

		return subject;
	}

	public Subject removeSubject(Subject subject) {
		getSubjects().remove(subject);
		subject.setCourse(null);

		return subject;
	}

}