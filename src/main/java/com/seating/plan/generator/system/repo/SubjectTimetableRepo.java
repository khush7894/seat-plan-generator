package com.seating.plan.generator.system.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.seating.plan.generator.system.entity.SubjectTimetable;

public interface SubjectTimetableRepo extends JpaRepository<SubjectTimetable, Integer> {

	@Query("select e from SubjectTimetable e where e.isDeleted = 0 and e.examDate =:examDate")
	List<SubjectTimetable> findByExamDate(@Param("examDate") Date examDate);

	
}
