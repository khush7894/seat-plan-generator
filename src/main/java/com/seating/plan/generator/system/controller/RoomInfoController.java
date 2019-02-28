package com.seating.plan.generator.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seating.plan.generator.system.entity.RoomInfo;
import com.seating.plan.generator.system.service.RoomInfoService;
import com.seating.plan.generator.system.util.RestResponse;

@RestController
@RequestMapping("/roomInfo")
public class RoomInfoController {

	@Autowired
	private RoomInfoService roomInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(SubjectTimetableController.class);

	
	@RequestMapping(value = "/bulkInsert", method = RequestMethod.POST, headers = ("content-type=multipart/*"), produces = "application/json", consumes = "application/json")
	public RestResponse saveSubjectTimetable(@RequestParam(value = "roomInfo", required = true)  MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
		try {

			String message = roomInfoService.bulkSaveRoomInfo(file);
			logger.info(message);
			return RestResponse.build().withSuccess(message);

		} catch (Exception e) {
			logger.error("Failed to add bulk room info due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to add bulk room info due to:" + e.getMessage());
		}

}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public RestResponse findAll() {
		try {
			logger.info("find all room info");
			List<RoomInfo> roomInfoList = roomInfoService.findAll();
			if (!roomInfoList.isEmpty()) {
				logger.info("find all room info successfully");
				return RestResponse.build().withSuccess().withData(roomInfoList);
			} else {
				return RestResponse.build().withError("no data found");
			}
		} catch (Exception e) {
			logger.error("Failed to find all room info due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find all room info due to:" + e.getMessage());
		}
	}
	
	@RequestMapping( method = RequestMethod.DELETE, produces = "application/json")
	public RestResponse deleteRoomInfo(HttpServletRequest request, HttpServletResponse response) {
		try {

			roomInfoService.deleteRoomInfo();
			logger.info("Data deleted successfully");
			return RestResponse.build().withSuccess("Data deleted successfully");

		} catch (Exception e) {
			logger.error("Failed to delete bulk room info due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to delete bulk room info due to:" + e.getMessage());
		}

	}
	
	
}
