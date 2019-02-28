package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-12T15:52:04.032+0530")
@StaticMetamodel(UserInstitute.class)
public class UserInstitute_ {
	public static volatile SingularAttribute<UserInstitute, Integer> id;
	public static volatile SingularAttribute<UserInstitute, Date> createdDate;
	public static volatile SingularAttribute<UserInstitute, Integer> isDeleted;
	public static volatile SingularAttribute<UserInstitute, Date> updatedDate;
	public static volatile SingularAttribute<UserInstitute, Institute> institute;
	public static volatile SingularAttribute<UserInstitute, User> user;
}
