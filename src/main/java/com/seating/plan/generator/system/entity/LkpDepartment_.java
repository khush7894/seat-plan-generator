package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-12T15:52:04.024+0530")
@StaticMetamodel(LkpDepartment.class)
public class LkpDepartment_ {
	public static volatile SingularAttribute<LkpDepartment, Integer> id;
	public static volatile SingularAttribute<LkpDepartment, String> code;
	public static volatile SingularAttribute<LkpDepartment, Date> createdDate;
	public static volatile SingularAttribute<LkpDepartment, Integer> isDeleted;
	public static volatile SingularAttribute<LkpDepartment, String> name;
	public static volatile SingularAttribute<LkpDepartment, Date> updatedDate;
	public static volatile ListAttribute<LkpDepartment, Department> departments;
}
