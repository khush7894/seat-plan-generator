package com.seating.plan.generator.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seating.plan.generator.system.entity.LkpSection;

@Repository
public interface LkpSectionRepo extends JpaRepository<LkpSection, Integer> {

	@Query("select e from LkpSection e where e.isDeleted = 0")
	List<LkpSection> findAll();
}
