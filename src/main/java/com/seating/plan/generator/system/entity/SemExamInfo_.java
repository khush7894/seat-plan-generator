package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-28T17:35:09.731+0530")
@StaticMetamodel(SemExamInfo.class)
public class SemExamInfo_ {
	public static volatile SingularAttribute<SemExamInfo, Integer> id;
	public static volatile SingularAttribute<SemExamInfo, Date> createdDate;
	public static volatile SingularAttribute<SemExamInfo, String> examNameC40;
	public static volatile SingularAttribute<SemExamInfo, String> exctrlC6;
	public static volatile SingularAttribute<SemExamInfo, Integer> isDeleted;
	public static volatile SingularAttribute<SemExamInfo, Date> updatedDate;
}
