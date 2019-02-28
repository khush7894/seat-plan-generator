package com.seating.plan.generator.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seating.plan.generator.system.entity.SubjectInfo;

public interface SubjectInfoRepo extends JpaRepository<SubjectInfo, Integer>{

	@Query("select e from SubjectInfo e where e.isDeleted = 0 and e.subcodeC2 =:subjectCode")
	SubjectInfo findbySubjectCode(@Param("subjectCode") String subjectCode);
	
	

}
