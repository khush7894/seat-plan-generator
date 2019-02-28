package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-12T15:52:04.021+0530")
@StaticMetamodel(Institute.class)
public class Institute_ {
	public static volatile SingularAttribute<Institute, Integer> id;
	public static volatile SingularAttribute<Institute, String> address;
	public static volatile SingularAttribute<Institute, String> alias;
	public static volatile SingularAttribute<Institute, String> code;
	public static volatile SingularAttribute<Institute, String> contactOne;
	public static volatile SingularAttribute<Institute, String> contactTwo;
	public static volatile SingularAttribute<Institute, Date> createdDate;
	public static volatile SingularAttribute<Institute, String> email;
	public static volatile SingularAttribute<Institute, Integer> isDeleted;
	public static volatile SingularAttribute<Institute, String> logo;
	public static volatile SingularAttribute<Institute, String> name;
	public static volatile SingularAttribute<Institute, String> registrationNo;
	public static volatile SingularAttribute<Institute, Date> updatedDate;
	public static volatile SingularAttribute<Institute, String> website;
	public static volatile ListAttribute<Institute, Department> departments;
	public static volatile SingularAttribute<Institute, City> city;
	public static volatile ListAttribute<Institute, UserInstitute> userInstitutes;
}
