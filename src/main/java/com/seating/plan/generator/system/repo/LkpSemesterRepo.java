package com.seating.plan.generator.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seating.plan.generator.system.entity.LkpSemester;

@Repository
public interface LkpSemesterRepo extends JpaRepository<LkpSemester, Integer> {

	@Query("select e from LkpSemester e where e.isDeleted = 0")
	List<LkpSemester> findAll();
}
