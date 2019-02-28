package com.seating.plan.generator.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seating.plan.generator.system.entity.Batch;

public interface BatchRepo extends JpaRepository<Batch, Integer> {

	@Modifying
	@Query("update Batch e set e.isDeleted = 1 where e.id = :id")
	int markDeleted(@Param("id") Integer id);

	@Query("select e from Batch e where e.isDeleted = 0 and e.course.department.institute.id=:instituteId and e.course.id=:courseId")
	List<Batch> findByCourseId(@Param("instituteId") Integer instituteId, @Param("courseId") Integer courseId);

	// @Query("select e from Batch e where e.isDeleted=0 and e.batch.id=:batchId")
	// findOne(@Param("batchId") Integer batchId);
}