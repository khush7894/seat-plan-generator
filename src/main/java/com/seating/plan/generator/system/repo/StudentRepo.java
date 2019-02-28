package com.seating.plan.generator.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seating.plan.generator.system.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

	@Query("select e from Student e join BatchSectionStudent bss on e.id = bss.student.id where bss.batch.id=:batchId")
	List<Student> findByStudnet(@Param("batchId") Integer batchId);
}