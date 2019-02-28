package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-28T17:35:09.747+0530")
@StaticMetamodel(SubjectTimetable.class)
public class SubjectTimetable_ {
	public static volatile SingularAttribute<SubjectTimetable, Integer> id;
	public static volatile SingularAttribute<SubjectTimetable, Date> createdDate;
	public static volatile SingularAttribute<SubjectTimetable, Date> examDate;
	public static volatile SingularAttribute<SubjectTimetable, Integer> isDeleted;
	public static volatile SingularAttribute<SubjectTimetable, String> shift;
	public static volatile SingularAttribute<SubjectTimetable, String> subjectCode;
	public static volatile SingularAttribute<SubjectTimetable, String> subjectName;
	public static volatile SingularAttribute<SubjectTimetable, Date> updatedDate;
}
