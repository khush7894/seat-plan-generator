package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-12T15:52:04.017+0530")
@StaticMetamodel(BatchSectionStudent.class)
public class BatchSectionStudent_ {
	public static volatile SingularAttribute<BatchSectionStudent, Integer> id;
	public static volatile SingularAttribute<BatchSectionStudent, Date> createdDate;
	public static volatile SingularAttribute<BatchSectionStudent, Integer> isDeleted;
	public static volatile SingularAttribute<BatchSectionStudent, Date> updatedDate;
	public static volatile SingularAttribute<BatchSectionStudent, Batch> batch;
	public static volatile SingularAttribute<BatchSectionStudent, LkpSection> lkpSection;
	public static volatile SingularAttribute<BatchSectionStudent, LkpSemester> lkpSemester;
	public static volatile SingularAttribute<BatchSectionStudent, Student> student;
}
