package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-12T15:52:04.028+0530")
@StaticMetamodel(LkpSection.class)
public class LkpSection_ {
	public static volatile SingularAttribute<LkpSection, Integer> id;
	public static volatile SingularAttribute<LkpSection, Date> createdDate;
	public static volatile SingularAttribute<LkpSection, Integer> isDeleted;
	public static volatile SingularAttribute<LkpSection, String> name;
	public static volatile SingularAttribute<LkpSection, Date> updatedDate;
	public static volatile ListAttribute<LkpSection, BatchSectionStudent> batchSectionStudents;
}
