package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-27T19:59:55.089+0530")
@StaticMetamodel(LkpSemester.class)
public class LkpSemester_ {
	public static volatile SingularAttribute<LkpSemester, Integer> id;
	public static volatile SingularAttribute<LkpSemester, Date> createdDate;
	public static volatile SingularAttribute<LkpSemester, Integer> isDeleted;
	public static volatile SingularAttribute<LkpSemester, String> name;
	public static volatile SingularAttribute<LkpSemester, Date> updatedDate;
	public static volatile ListAttribute<LkpSemester, BatchSectionStudent> batchSectionStudents;
	public static volatile ListAttribute<LkpSemester, Subject> subjects;
}
