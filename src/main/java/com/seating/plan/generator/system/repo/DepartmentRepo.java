package com.seating.plan.generator.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seating.plan.generator.system.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

	@Modifying
	@Query("update Department  e set e.isDeleted = 1 where e.id = :id")
	int markDeleted(@Param("id") Integer id);

	@Query("select e from Department e where e.isDeleted = 0 and e.id = :id")
	Department findById(@Param("id") Integer id);

	@Query("select e from Department e where e.isDeleted = 0 and e.institute.id = :instituteId")
	List<Department> findByInstituteId(@Param("instituteId") Integer instituteId);

}
