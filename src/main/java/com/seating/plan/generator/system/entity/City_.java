package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-12T15:52:04.018+0530")
@StaticMetamodel(City.class)
public class City_ {
	public static volatile SingularAttribute<City, Integer> id;
	public static volatile SingularAttribute<City, Date> createdDate;
	public static volatile SingularAttribute<City, Integer> isDeleted;
	public static volatile SingularAttribute<City, String> name;
	public static volatile SingularAttribute<City, Integer> pincode;
	public static volatile SingularAttribute<City, Date> updatedDate;
	public static volatile ListAttribute<City, Institute> institutes;
	public static volatile ListAttribute<City, User> users;
}
