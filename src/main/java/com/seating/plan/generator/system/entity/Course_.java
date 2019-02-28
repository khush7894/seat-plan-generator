package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-27T19:59:54.981+0530")
@StaticMetamodel(Course.class)
public class Course_ {
	public static volatile SingularAttribute<Course, Integer> id;
	public static volatile SingularAttribute<Course, Date> createdDate;
	public static volatile SingularAttribute<Course, Integer> isDeleted;
	public static volatile SingularAttribute<Course, Date> updatedDate;
	public static volatile ListAttribute<Course, Batch> batches;
	public static volatile SingularAttribute<Course, Department> department;
	public static volatile SingularAttribute<Course, LkpCourse> lkpCourse;
	public static volatile ListAttribute<Course, Subject> subjects;
}
