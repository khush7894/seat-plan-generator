package com.seating.plan.generator.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seating.plan.generator.system.entity.Batch;
import com.seating.plan.generator.system.entity.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Integer> {

	

	@Query("select e from Subject e where e.isDeleted = 0 and e.lkpSemester.id=:lkpSemesterId and e.course.id=:courseId")
	List<Subject> findByCourseIdSemesterId(@Param("lkpSemesterId") Integer lkpSemesterId, @Param("courseId") Integer courseId);

	
}