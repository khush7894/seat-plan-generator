package com.seating.plan.generator.system.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seating.plan.generator.system.entity.City;
import com.seating.plan.generator.system.entity.Institute;
import com.seating.plan.generator.system.entity.User;
import com.seating.plan.generator.system.repo.CityRepo;
import com.seating.plan.generator.system.repo.InstituteRepo;
import com.seating.plan.generator.system.repo.UserRepo;
import com.seating.plan.generator.system.req.web.view.InstituteReqView;
import com.seating.plan.generator.system.util.DateHelper;
import com.seating.plan.generator.system.view.InstituteRespView;

@Transactional
@Service
public class InstituteService {

	private static final Logger logger = LoggerFactory.getLogger(InstituteService.class);

	@Autowired
	private InstituteRepo instituteRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private UserRepo userRepo;

	public void save(InstituteReqView instituteView, Integer userId) throws Exception {

		Institute institute = new Institute();

		institute.setId(instituteView.getId());


		institute.setAddress(instituteView.getAddress());
		institute.setAlias(instituteView.getAlias());
		institute.setCode(instituteView.getCode());
		institute.setEmail(instituteView.getEmail());
		institute.setName(instituteView.getName());
		institute.setContactOne(instituteView.getContactOne());
		institute.setContactTwo(instituteView.getContactTwo());
		institute.setWebsite(instituteView.getWebsite());
		institute.setCity(cityRepo.findOne(instituteView.getCityId()));
		institute.setRegistrationNo(instituteView.getRegistrationNo());
		institute.setCreatedDate(DateHelper.getCurrentDate());
		institute.setIsDeleted(0);

		instituteRepo.save(institute);
	}

	public String update(InstituteReqView instituteView, Integer instituteId) {
		logger.info("Fetching entity for cityId:{} ", instituteView.getCityId());
		City city = cityRepo.findOne(instituteView.getCityId());
		logger.info("Fetched entity for cityId:{}, cityName:{}", city.getId(), city.getName());

		Institute ref = instituteRepo.findOne(instituteView.getId());
		Institute institute = new Institute();
		institute.setId(instituteView.getId());
		institute.setAddress(instituteView.getAddress());
		institute.setEmail(instituteView.getEmail());
		institute.setName(instituteView.getName());
		institute.setContactOne(instituteView.getContactOne());
		institute.setContactTwo(instituteView.getContactTwo());
		institute.setWebsite(instituteView.getWebsite());
		institute.setCity(city);
		institute.setLogo(instituteView.getLogo());
		institute.setUpdatedDate(DateHelper.getCurrentDate());
		institute.setCreatedDate(ref.getCreatedDate());
		institute.setIsDeleted(ref.getIsDeleted());
		institute.setLogo(instituteView.getLogo());
		institute.setAlias(ref.getAlias());
		institute.setCode(ref.getCode());
		institute.setRegistrationNo(ref.getRegistrationNo());

		if (instituteView.getLogo() == null) {
			institute.setLogo(ref.getLogo());
		}
		Institute saveInstitute = instituteRepo.save(institute);
		if (saveInstitute != null) {
			return "done";
		}
		return "fail";
	}

	public Institute findById(Integer id) {
		return instituteRepo.findById(id);
	}

	public List<Institute> findByAlias(String alias) {
		return instituteRepo.findByAlias(alias);
	}



	public List<InstituteRespView> findByUserId(Integer instituteId) {
		List<InstituteRespView> instituteRespViewlist = new ArrayList<>();
		List<Institute> instituteList = instituteRepo.findByUserId(instituteId);

		for (Institute institute : instituteList) {
			InstituteRespView instituteRespView = new InstituteRespView();
			instituteRespView.setId(institute.getId());
			instituteRespView.setAddress(institute.getAddress());
			instituteRespView.setEmail(institute.getEmail());
			instituteRespView.setName(institute.getName());
			instituteRespView.setContactOne(institute.getContactOne());
			instituteRespView.setContactTwo(institute.getContactTwo());
			instituteRespView.setWebsite(institute.getWebsite());
			instituteRespView.setLogo(institute.getLogo());
			instituteRespView.setAlias(institute.getAlias());
			instituteRespView.setCode(institute.getCode());
			instituteRespView.setRegistrationNo(institute.getRegistrationNo());
			instituteRespViewlist.add(instituteRespView);
		}

		return instituteRespViewlist;
	}

	public int markDeleted(Integer id) {
		return instituteRepo.markDeleted(id);

	}

}
