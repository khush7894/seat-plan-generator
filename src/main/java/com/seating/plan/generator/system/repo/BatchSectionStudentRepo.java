package com.seating.plan.generator.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seating.plan.generator.system.entity.BatchSectionStudent;

public interface BatchSectionStudentRepo extends JpaRepository<BatchSectionStudent, Integer> {
	@Query("select e from BatchSectionStudent e where e.isDeleted = 0 and e.id = :id")
	BatchSectionStudent findById(@Param("id") Integer id);
	

	@Query("select e from BatchSectionStudent e where e.isDeleted = 0 and e.batch.course.department.institute.id=:instituteId and e.batch.course.id = :courseId")
	List<BatchSectionStudent> findByCourseId(@Param("instituteId") Integer instituteId, @Param("courseId") Integer courseId);

	@Query("select e from BatchSectionStudent e where e.isDeleted = 0 and e.batch.course.department.institute.id=:instituteId and e.batch.id = :batchId")
	List<BatchSectionStudent> findByBatchIdByInstituteId(@Param("instituteId") Integer instituteId, @Param("batchId") Integer batchId);

	@Query("select e from BatchSectionStudent e where e.isDeleted = 0 and e.batch.id = :batchId")
	List<BatchSectionStudent> findByBatchId(@Param("batchId") Integer batchId);


}
