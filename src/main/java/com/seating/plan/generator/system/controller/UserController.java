
package com.seating.plan.generator.system.controller;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seating.plan.generator.system.entity.User;
import com.seating.plan.generator.system.repo.UserRepo;
import com.seating.plan.generator.system.req.mob.view.ChangePasswordReqView;
import com.seating.plan.generator.system.req.mob.view.LoginUserReqView;
import com.seating.plan.generator.system.req.mob.view.UserForgetPasswordMobReqView;
import com.seating.plan.generator.system.resp.mob.view.LoginUserRespView;
import com.seating.plan.generator.system.service.FileService;
import com.seating.plan.generator.system.service.UserService;
import com.seating.plan.generator.system.util.RestResponse;
import com.seating.plan.generator.system.util.RestUriConstants;
@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserService userService;

	@Autowired
	public UserRepo userRepo;

	@Autowired
	private FileService fileService;

	Integer instituteId = 1;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public RestResponse l(@RequestBody LoginUserReqView loginUserReqView) {
		try {
			logger.info("user login for email-id :{}", loginUserReqView.getEmail());
			LoginUserRespView loginUserRespView = userService.loginByEmailPassword(loginUserReqView.getEmail(), loginUserReqView.getContactOne(), loginUserReqView.getPassword());

			if (loginUserRespView != null) {
				logger.info("User login successfully, email-id:{}, user-id:{}", loginUserRespView.getEmail(), loginUserRespView.getId());
				return RestResponse.build().withData(loginUserRespView).withSuccess("reseller User Login successfully.");

			} else {
				logger.info("User email password is incorrect.");
				return RestResponse.build().withError("User email password is incorrect.");
			}
		} catch (Exception e) {
			logger.error("Failed to login user due to  {}:", e.toString(), e);
			return RestResponse.build().withError("Failed to login user due to :" + e.toString());
		}
	}


	@RequestMapping(value = "/changePassword", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public RestResponse ChangePassword(@RequestBody ChangePasswordReqView changePasswordReqView) {
		try {
			logger.info("update user NewPassword:{}", changePasswordReqView);

			userService.ChangePassword(changePasswordReqView);
		} catch (Exception e) {
			logger.error("Failed to update user Newpassword due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to update user Newpassword due to :" + e.getMessage());
		}
		logger.info("changePassword is successfully{}.", changePasswordReqView.getId());
		return RestResponse.build().withSuccess("NewPassword update for login.");

	}

	@RequestMapping(value = "/image/{info:.+}", method = RequestMethod.GET, produces = "image/jpeg")
	public byte[] getImage(@PathVariable("info") String info) {

		try {
			logger.info("get user image with info:{}", info);
			if (info.endsWith(FileService.IMAGE_SUFFIX)) {
				return getImageWithName(info);

			} else {
				return getImageWithId(Integer.parseInt(info));
			}
		} catch (Exception e) {
			logger.error("Failed to get user's Image due to:{}", e.toString(), e);
			throw new IllegalArgumentException("No image found due to:" + e, e);
		}

	}

	private byte[] getImageWithId(Integer id) throws Exception {
		logger.info("get user image for id:{}", id);
		User entity = userRepo.findOne(id);
		byte[] data = fileService.getImageDataWithURI(entity.getProfilePhoto());
		logger.info("returning image with size:{} ", data.length);
		return data;
	}

	private byte[] getImageWithName(String name) throws Exception {
		logger.info("get user image for name:{}", name);
		byte[] data = fileService.getImageDataWithName(RestUriConstants.USER, name);
		logger.info("returning image with size:{} ", data.length);
return Base64.getEncoder().encode(data);
		//return data;

	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	// public RestResponse delete(@PathVariable("id") Integer id) {
	// try {
	// logger.info("User delete by user userId: {}", id);
	// int userDelete = userService.delete(id);
	// if (userDelete > 0) {
	// return RestResponse.build().withSuccess("Data Deleted SuccessFully");
	// } else {
	// return RestResponse.build().withError("No data deleted");
	// }
	// } catch (Exception e) {
	// logger.error("Failed to delete user due to {}:", e.toString(), e);
	// return RestResponse.build().withError("Failed to delete user due to :" + e.getMessage());
	// }
	// }

}