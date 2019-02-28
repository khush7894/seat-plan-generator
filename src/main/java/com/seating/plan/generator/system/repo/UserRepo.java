package com.seating.plan.generator.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seating.plan.generator.system.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Modifying
	@Query("update User e set e.password=:newPassword where e.id =:id and e.isDeleted = 0")
	User ChangePassword(@Param("newPassword") String newPassword);

	@Modifying
	@Query("update User e set e.password = :password where e.email = :email")
	int forgetPassword(@Param("password") String password, @Param("email") String email);

	@Modifying
	@Query("update User e set e.isDeleted = 1 where e.id =:id")
	int markDeleted(@Param("id") Integer id);

	@Query("select e from User e where e.isDeleted = 0 and e.id =:id")
	User findOneById(@Param("id") Integer id);

	@Query("select e from User e where e.isDeleted = 0 and e.email=:email ")
	User findOneByEmail(@Param("email") String email);

	@Query("select e from User e where e.isDeleted = 0 and e.id=:id ")
	User findById(@Param("id") Integer id);

	@Query("select e from User e where e.isDeleted = 0 and e.email=:email or e.contactOne =:contactOne and e.password=:password")
	User loginByEmailPassword(@Param("email") String email, @Param("contactOne") String contactOne, @Param("password") String password);

//	@Query("select e from User e join BatchSectionStudent bss on e.id = bss.user.id where e.isDeleted=0 and bss.batch.course.department.institute.id=:instituteId and e.id=:userId")
//	User findById(@Param("instituteId") Integer instituteId, @Param("userId") Integer userId);

}