package com.seating.plan.generator.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seating.plan.generator.system.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {
	
	@Modifying
	@Query("update Course e set e.isDeleted = 1 where e.id = :id")
	int markDeleted(@Param("id") Integer id);

	@Query("select e from Course e where e.isDeleted = 0 and e.department.institute.id=:instituteId and e.department.id= :departmentId")
	List<Course> findByDepartment(@Param("instituteId") Integer instituteId, @Param("departmentId") Integer departmentId);

	@Query("select e from Course e where e.isDeleted = 0 and e.department.institute.id=:instituteId and e.id=:id")
	Course findByCourseId(@Param("instituteId") Integer instituteId, @Param("id") Integer courseId);

}