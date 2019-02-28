package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-12T15:52:04.029+0530")
@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Integer> id;
	public static volatile SingularAttribute<Role, Date> createdDate;
	public static volatile SingularAttribute<Role, Integer> isDeleted;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile SingularAttribute<Role, Date> updatedDate;
}
