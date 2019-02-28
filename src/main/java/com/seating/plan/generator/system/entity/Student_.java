package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-12T15:52:04.030+0530")
@StaticMetamodel(Student.class)
public class Student_ {
	public static volatile SingularAttribute<Student, Integer> id;
	public static volatile SingularAttribute<Student, String> contactOne;
	public static volatile SingularAttribute<Student, String> contactTwo;
	public static volatile SingularAttribute<Student, Date> createdDate;
	public static volatile SingularAttribute<Student, Date> dob;
	public static volatile SingularAttribute<Student, String> email;
	public static volatile SingularAttribute<Student, String> enrollmentNo;
	public static volatile SingularAttribute<Student, String> fatherName;
	public static volatile SingularAttribute<Student, String> firstName;
	public static volatile SingularAttribute<Student, String> gender;
	public static volatile SingularAttribute<Student, Integer> isDeleted;
	public static volatile SingularAttribute<Student, String> lastName;
	public static volatile SingularAttribute<Student, String> motherName;
	public static volatile SingularAttribute<Student, String> rollNumber;
	public static volatile SingularAttribute<Student, Date> updatedDate;
	public static volatile ListAttribute<Student, BatchSectionStudent> batchSectionStudents;
}
