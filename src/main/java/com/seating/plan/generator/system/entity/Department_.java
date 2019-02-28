package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-12T16:03:02.976+0530")
@StaticMetamodel(Department.class)
public class Department_ {
	public static volatile SingularAttribute<Department, Integer> id;
	public static volatile SingularAttribute<Department, Date> createdDate;
	public static volatile SingularAttribute<Department, Integer> isDeleted;
	public static volatile SingularAttribute<Department, Date> updatedDate;
	public static volatile ListAttribute<Department, Course> courses;
	public static volatile SingularAttribute<Department, Institute> institute;
	public static volatile SingularAttribute<Department, LkpDepartment> lkpDepartment;
}
