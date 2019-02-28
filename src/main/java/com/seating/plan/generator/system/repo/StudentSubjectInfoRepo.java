package com.seating.plan.generator.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seating.plan.generator.system.entity.StudentSubjectInfo;

@Repository
public interface StudentSubjectInfoRepo extends JpaRepository<StudentSubjectInfo, Integer>{

	@Query("select e from StudentSubjectInfo e where e.isDeleted = 0 and UPPER(e.actSubjectC8) like UPPER(:subjectCode)")
	List<StudentSubjectInfo> findbySubjectCode(@Param("subjectCode") String subjectCode);

	
	
}
