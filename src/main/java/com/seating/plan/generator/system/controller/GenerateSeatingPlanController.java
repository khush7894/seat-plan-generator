package com.seating.plan.generator.system.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seating.plan.generator.system.req.web.view.SeatingPlanGenerateReqView;
import com.seating.plan.generator.system.service.BatchService;
import com.seating.plan.generator.system.service.FileService;
import com.seating.plan.generator.system.service.GenerateSeatingPlanService;
import com.seating.plan.generator.system.util.RestResponse;

@RestController
@RequestMapping("/generateSeatingPlan")
public class GenerateSeatingPlanController {

	private static final Logger logger = LoggerFactory.getLogger(GenerateSeatingPlanController.class);

	@Autowired
	private GenerateSeatingPlanService generateSeatingPlanService;

	@Autowired
	private FileService fileService;

	@RequestMapping(method = RequestMethod.POST, headers = ("content-type=multipart/*"), produces = "application/json", consumes = "application/json")
	public RestResponse save(@RequestParam(value = "inputJson", required = false) String inputJson, @RequestParam(value = "studentListFile", required = false) MultipartFile inputFile) {
		try {
			logger.info("Input Json:{}", inputJson);
			if (inputFile != null) {
				logger.info("Student bulk upload File-Nmae:{}", inputFile.getOriginalFilename());
			}
			ObjectMapper mapper = new ObjectMapper();
			SeatingPlanGenerateReqView seatingPlanGenerateReqView = mapper.readValue(inputJson, SeatingPlanGenerateReqView.class);

			logger.info("Generate seating plan request view:{}", seatingPlanGenerateReqView);

			List<Map<String, Object>> seatingPlanList = generateSeatingPlanService.createSeatingPlan(seatingPlanGenerateReqView, inputFile);
			return RestResponse.build().withSuccess().withData(seatingPlanList);

		} catch (Exception e) {
			logger.error("Failed to generate due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find generator seating plan to :" + e.getMessage());

		}

	}

	/*
	 * Code for Pdf generator
	 * 
	 *
	 * @RequestMapping(method = RequestMethod.POST, headers =
	 * ("content-type=multipart/*"), produces = "application/pdf", consumes =
	 * "application/json") public ResponseEntity<?> save(@RequestParam(value =
	 * "inputJson", required = false) String inputJson, @RequestParam(value =
	 * "studentListFile", required = true) MultipartFile inputFile) {
	 * ResponseEntity<?> response = null; try { logger.info("Input Json:{}",
	 * inputJson);
	 * 
	 * logger.info("Student bulk upload File-Nmae:{}",
	 * inputFile.getOriginalFilename());
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); SeatingPlanGenerateReqView
	 * seatingPlanGenerateReqView = mapper.readValue(inputJson,
	 * SeatingPlanGenerateReqView.class);
	 * 
	 * logger.info("Generate seating plan request view:{}",
	 * seatingPlanGenerateReqView);
	 * 
	 * String filePath =
	 * generateSeatingPlanService.createSeatingPlan(seatingPlanGenerateReqView,
	 * inputFile); File file = new File(filePath); InputStream in = new
	 * FileInputStream(file); HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.parseMediaType("application/pdf"));
	 * headers.add("Access-Control-Allow-Origin", "*");
	 * headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
	 * headers.add("Access-Control-Allow-Headers", "Content-Type");
	 * headers.add("Content-Disposition", "filename=" + "SeatingPlan.pdf");
	 * headers.setContentLength(file.length());
	 * 
	 * byte[] byteArray = FileUtils.readFileToByteArray(file); response = new
	 * ResponseEntity<byte[]>(byteArray, null, HttpStatus.OK); } catch
	 * (Exception e) { logger.error("Failed to generate due to:{}",
	 * e.toString(), e); List<Map<String, String>> errorResponseList = new
	 * ArrayList<>(); Map<String, String> errorResponseMap = new HashMap<>();
	 * errorResponseMap.put("statue", "Error"); errorResponseMap.put("message",
	 * e.getMessage()); errorResponseList.add(errorResponseMap);
	 * 
	 * response = new ResponseEntity<List<Map<String,
	 * String>>>(errorResponseList, null, HttpStatus.FORBIDDEN);
	 * 
	 * } return response;
	 * 
	 * }
	 * 
	 */

}
