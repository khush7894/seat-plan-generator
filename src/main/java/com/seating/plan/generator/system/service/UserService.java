package com.seating.plan.generator.system.service;

import java.util.List;

import org.apache.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seating.plan.generator.system.controller.UserController;
import com.seating.plan.generator.system.entity.User;
import com.seating.plan.generator.system.repo.CityRepo;
import com.seating.plan.generator.system.repo.UserRepo;
import com.seating.plan.generator.system.req.mob.view.ChangePasswordReqView;
import com.seating.plan.generator.system.req.web.view.UserReqView;
import com.seating.plan.generator.system.resp.mob.view.CityRespView;
import com.seating.plan.generator.system.resp.mob.view.LoginUserRespView;
import com.seating.plan.generator.system.resp.mob.view.StateRespView;

@Transactional
@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired(required = true)
	public UserRepo userRepo;

	@Autowired(required = true)
	public CityRepo cityRepo;

	// Forget Password.............

	@Transactional

	public void update(UserReqView userView) {
		User user = new User();
		user.setFirstName(userView.getFirstName());
		user.setLastName(userView.getLastName());

		user.setContactOne(userView.getContactOne());
		user.setContactTwo(userView.getContactTwo());
		user.setGender(userView.getGender());
		user.setPassword(userView.getPassword());
		user.setIsDeleted(0);
		userRepo.save(user);

	}

	public void ChangePassword(ChangePasswordReqView changePasswordReqView) {

		String oldPasswordReq = changePasswordReqView.getOldPassword();
		Integer userId = changePasswordReqView.getId();
		User user = userRepo.findOneById(userId);
		String OldPassword = user.getPassword();
		if (OldPassword.equals(oldPasswordReq)) {
			User userTwo = new User();
			user.setPassword(changePasswordReqView.getNewPassword());
			userRepo.save(user);
		}
		userRepo.save(user);
	}

	// Login
	public LoginUserRespView loginByEmailPassword(String email, String contactOne, String password) {
		User user = userRepo.loginByEmailPassword(email, contactOne, password);
		LoginUserRespView loginUserView = null;
		CityRespView cityRespView = new CityRespView();
		StateRespView stateRespView = new StateRespView();

		if (user != null && StringUtils.isNotEmpty(user.getFirstName())) {
			loginUserView = new LoginUserRespView();
			loginUserView.setId(user.getId());
			loginUserView.setFirstName(user.getFirstName());
			loginUserView.setLastName(user.getLastName());
			loginUserView.setEmail(user.getEmail());
			loginUserView.setGender(user.getGender());
			loginUserView.setContactOne(user.getContactOne());
			loginUserView.setContactTwo(user.getContactTwo());
			loginUserView.setProfilePhoto(user.getProfilePhoto());
			loginUserView.setCity(cityRespView);

		}
		return loginUserView;
	}

	public User findOneByEmail(String email) {
		return userRepo.findOneByEmail(email);
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findOneById(Integer id) {
		User user = userRepo.findOneById(id);
		return user;
	}

	// public UserReqView findById(Integer instituteId, Integer userId) {
	// User user = userRepo.findById(instituteId, userId);
	// UserReqView userReqView = null;
	// user.getStudentDetails().size();
	//
	//
	// return userReqView;
	//
	// }

	// public int delete(Integer id) {
	// return userRepo.markDeleted(id);
	// }

}