package com.seating.plan.generator.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seating.plan.generator.system.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	@Modifying
	@Query("update Role e set e.isDeleted = 1 where e.id = :id")
	int markDeleted(@Param("id") Integer id);

	@Query("select e from Role e where e.isDeleted = 0 and e.id = :id")
	Role findById(@Param("id") Integer id);

	@Query("select e from Role e where e.isDeleted = 0 and e.name = :name")
	Role findByName(@Param("name") String name);

}
