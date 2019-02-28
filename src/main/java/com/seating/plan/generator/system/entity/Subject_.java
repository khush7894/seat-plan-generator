package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-27T19:59:55.150+0530")
@StaticMetamodel(Subject.class)
public class Subject_ {
	public static volatile SingularAttribute<Subject, Integer> id;
	public static volatile SingularAttribute<Subject, String> code;
	public static volatile SingularAttribute<Subject, Date> createdDate;
	public static volatile SingularAttribute<Subject, Integer> isDeleted;
	public static volatile SingularAttribute<Subject, String> name;
	public static volatile SingularAttribute<Subject, Date> updatedDate;
	public static volatile SingularAttribute<Subject, Course> course;
	public static volatile SingularAttribute<Subject, LkpSemester> lkpSemester;
}
