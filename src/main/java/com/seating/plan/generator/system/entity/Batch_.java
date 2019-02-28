package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-12T15:53:08.149+0530")
@StaticMetamodel(Batch.class)
public class Batch_ {
	public static volatile SingularAttribute<Batch, Integer> id;
	public static volatile SingularAttribute<Batch, String> code;
	public static volatile SingularAttribute<Batch, Date> createdDate;
	public static volatile SingularAttribute<Batch, String> description;
	public static volatile SingularAttribute<Batch, Date> endYear;
	public static volatile SingularAttribute<Batch, Integer> isDeleted;
	public static volatile SingularAttribute<Batch, String> name;
	public static volatile SingularAttribute<Batch, Date> startYear;
	public static volatile SingularAttribute<Batch, Date> updatedDate;
	public static volatile SingularAttribute<Batch, Course> course;
	public static volatile ListAttribute<Batch, BatchSectionStudent> batchSectionStudents;
}
