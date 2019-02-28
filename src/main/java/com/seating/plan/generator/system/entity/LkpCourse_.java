package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-12T15:52:04.023+0530")
@StaticMetamodel(LkpCourse.class)
public class LkpCourse_ {
	public static volatile SingularAttribute<LkpCourse, Integer> id;
	public static volatile SingularAttribute<LkpCourse, String> code;
	public static volatile SingularAttribute<LkpCourse, Date> createdDate;
	public static volatile SingularAttribute<LkpCourse, String> duration;
	public static volatile SingularAttribute<LkpCourse, Integer> isDeleted;
	public static volatile SingularAttribute<LkpCourse, String> name;
	public static volatile SingularAttribute<LkpCourse, String> totalSemester;
	public static volatile SingularAttribute<LkpCourse, Date> updatedDate;
	public static volatile ListAttribute<LkpCourse, Course> courses;
}
