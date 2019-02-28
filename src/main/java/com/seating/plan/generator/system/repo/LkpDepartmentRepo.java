package com.seating.plan.generator.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seating.plan.generator.system.entity.LkpDepartment;

@Repository
public interface LkpDepartmentRepo extends JpaRepository<LkpDepartment, Integer> {

	@Query("select e from LkpDepartment e where e.isDeleted = 0 and e.id=:id")
	LkpDepartment findById(@Param("id") Integer id);

	@Query("select e from LkpDepartment e where e.isDeleted = 0")
	List<LkpDepartment> findAll();

}
