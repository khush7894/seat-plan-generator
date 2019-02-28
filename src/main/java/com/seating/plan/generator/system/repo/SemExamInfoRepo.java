package com.seating.plan.generator.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seating.plan.generator.system.entity.SemExamInfo;

public interface SemExamInfoRepo extends JpaRepository<SemExamInfo, Integer> {

	@Query("select e from SemExamInfo e where e.isDeleted = 0 and e.exctrlC6 =:exctrlC6")
	SemExamInfo findbyExctrt(@Param("exctrlC6") String exctrlC6);

}
