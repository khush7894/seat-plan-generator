package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-27T19:59:56.135+0530")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> address;
	public static volatile SingularAttribute<User, String> contactOne;
	public static volatile SingularAttribute<User, String> contactTwo;
	public static volatile SingularAttribute<User, Date> createdDate;
	public static volatile SingularAttribute<User, Date> dob;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> gender;
	public static volatile SingularAttribute<User, Integer> isDeleted;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> profilePhoto;
	public static volatile SingularAttribute<User, Date> updatedDate;
	public static volatile SingularAttribute<User, City> city;
	public static volatile ListAttribute<User, UserInstitute> userInstitutes;
}
