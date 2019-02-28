package com.seating.plan.generator.system.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seating.plan.generator.system.entity.Institute;
import com.seating.plan.generator.system.repo.InstituteRepo;
import com.seating.plan.generator.system.req.web.view.InstituteReqView;
import com.seating.plan.generator.system.service.FileService;
import com.seating.plan.generator.system.service.InstituteService;
import com.seating.plan.generator.system.util.RestResponse;
import com.seating.plan.generator.system.util.RestUriConstants;
import com.seating.plan.generator.system.view.InstituteRespView;

@RestController
@RequestMapping("/institutes")
public class InstituteController {
	private static final Logger logger = LoggerFactory.getLogger(InstituteController.class);

	@Autowired
	private InstituteService instituteService;

	@Autowired
	private FileService fileService;

	@Autowired
	private InstituteRepo instituteRepo;

	// save institutes
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public RestResponse save(@RequestBody InstituteReqView instituteView) {
		try {
			Integer userId = 49;
			logger.info("save institute:{}, userId:{}", instituteView, userId);
			instituteService.save(instituteView, userId);
		} catch (Exception e) {
			logger.error("Failed to save institutes due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to save institutes due to:" + e.getMessage());
		}
		return RestResponse.build().withSuccess("Institutes saved  successfully.");
	}

	// update
	@RequestMapping(value = "/update", method = RequestMethod.POST, headers = ("content-type=multipart/*"), produces = "application/json", consumes = "application/json")
	public RestResponse update(@RequestParam(value = "instituteId", required = false) Integer instituteId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "contactOne", required = false) String contactOne,
			@RequestParam(value = "contactTwo", required = false) String contactTwo,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "website", required = false) String website,
			@RequestParam(value = "cityId", required = false) Integer cityId,
			@RequestParam(value = "logo", required = false) MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {

		Integer userId;

		String logo = null;
		try {
			if (file != null) {
				logger.info("save with logo id:{} originalFileName:{}", file.getOriginalFilename());
				if (file.getSize() > fileService.getImageMaxAllowedSize()) {

					throw new Exception(
							"File size is larger then max allowed size :" + fileService.getImageMaxAllowedSize());
				}
				byte[] data = file.getBytes();
				logo = fileService.saveImage1(RestUriConstants.INSTITUTE, data);
			}
			InstituteReqView instituteReqView = new InstituteReqView();

			instituteReqView.setId(instituteId);
			instituteReqView.setName(name);
			instituteReqView.setAddress(address);
			instituteReqView.setContactOne(contactOne);
			instituteReqView.setContactTwo(contactTwo);
			instituteReqView.setEmail(email);
			instituteReqView.setCityId(cityId);
			instituteReqView.setWebsite(website);
			instituteReqView.setLogo(logo);

			logger.info("Institute:{}", instituteReqView);
			String message = instituteService.update(instituteReqView, instituteId);
			logger.info(message);
			return RestResponse.build().withSuccess(message);
		} catch (Exception e) {
			if (logo != null) {
				FileUtils.deleteQuietly(new File(fileService.getAbsolutePath(logo)));
			}

			logger.error("Failed to update institute due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to update institute due to :" + e.getMessage());
		}
	}

	// Find By Id
	@RequestMapping(value = "/ById/{id}", method = RequestMethod.GET, produces = "application/json")
	public RestResponse findById(@PathVariable("id") Integer id) {
		try {
			logger.info("find institutes by Id");
			Institute institute = instituteService.findById(id);
			if (institute != null) {
				logger.info("institutes retrival successful", id);
				return RestResponse.build().withSuccess().withData(institute);
			} else {
				return RestResponse.build().withError("no data found");
			}
		} catch (Exception e) {
			logger.error("Failed to retrieve institute by Id due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to retrieve  institute due to:" + e.getMessage());
		}

	}

	// Find By Alias
	@RequestMapping(value = "/ByAlias/{alias}", method = RequestMethod.GET, produces = "application/json")
	public RestResponse findByAlias(@PathVariable("alias") String alias) {
		try {
			logger.info("find institutes by Alias");
			List<Institute> institute = instituteService.findByAlias(alias);
			if (institute != null) {
				logger.info("institutes retrival by alias is successful", alias);
				return RestResponse.build().withSuccess().withData(institute);
			} else {
				return RestResponse.build().withError("no data fetched");
			}
		} catch (Exception e) {
			logger.error("Failed to retrieve institute by Alias due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to retrieve  institute due to:" + e.getMessage());
		}
	}


	// Find By UserId
	@RequestMapping(value = "/ByUser", method = RequestMethod.GET, produces = "application/json")
	public RestResponse findByUserId() {
		try {

			Integer userId = 1;
			logger.info("find institutes by userId{}", userId);
			List<InstituteRespView> instituteRespViewList = instituteService.findByUserId(userId);

			logger.info("Fetched institute list size:{} ", instituteRespViewList.size());
			return RestResponse.build().withSuccess("successfully institute retreived").withData(instituteRespViewList);

		} catch (Exception e) {
			logger.error("Failed to retrieve institute by institueId due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to retrieve institute due to:" + e.getMessage());
		}
	}

	/// Delete institutes ById
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public RestResponse delete(@PathVariable("id") Integer id) {
		try {
			logger.info("delete institue by id:{}", id);
			int count = instituteService.markDeleted(id);
			if (count > 0) {
				return RestResponse.build().withSuccess("institutes deleted successfully.");
			} else {
				return RestResponse.build().withSuccess("institutes not deleted successfully");
			}

		} catch (Exception e) {
			logger.error("Failed to delete institutes due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to delete institutes due to:" + e.getMessage());
		}
	}

	@RequestMapping(value = "/image/{info:.+}", method = RequestMethod.GET, produces = "image/jpeg")
	public byte[] getImage(@PathVariable("info") String info) {

		try {
			logger.info("get institute image with info:{}", info);
			if (info.endsWith(FileService.IMAGE_SUFFIX)) {
				return getImageWithName(info);

			} else {
				return getImageWithId(Integer.parseInt(info));
			}
		} catch (Exception e) {
			logger.error("Failed to get institute's Image due to:{}", e.toString(), e);
			throw new IllegalArgumentException("No image found due to:" + e, e);
		}

	}

	private byte[] getImageWithId(Integer id) throws Exception {
		logger.info("get institute image for id:{}", id);
		Institute entity = instituteRepo.findOne(id);
		byte[] data = fileService.getImageDataWithURI(entity.getLogo());
		logger.info("returning image with size:{} ", data.length);
		return data;
	}

	private byte[] getImageWithName(String name) throws Exception {
		logger.info("get institute image for name:{}", name);
		byte[] data = fileService.getImageDataWithName(RestUriConstants.INSTITUTE, name);
		logger.info("returning image with size:{} ", data.length);
		// return Base64.getEncoder().encode(data);
		return data;
	}

}
