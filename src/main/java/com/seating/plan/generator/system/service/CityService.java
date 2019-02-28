package com.seating.plan.generator.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seating.plan.generator.system.entity.City;
import com.seating.plan.generator.system.repo.CityRepo;
import com.seating.plan.generator.system.view.CityRespView;

@Transactional
@Service
public class CityService {

	@Autowired
	private CityRepo cityRepo;

	public List<CityRespView> findAll(Integer stateId) {
		List<City> cityList = cityRepo.findAll();

		List<CityRespView> cityRespViewList = new ArrayList<>();
		CityRespView cityRespView = null;
		for (City city : cityList) {
			cityRespView = new CityRespView();
			cityRespView.setId(city.getId());
			cityRespView.setName(city.getName());
			cityRespViewList.add(cityRespView);
		}
		return cityRespViewList;
	}
}
