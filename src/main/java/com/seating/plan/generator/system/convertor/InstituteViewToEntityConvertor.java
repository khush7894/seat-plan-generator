package com.seating.plan.generator.system.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seating.plan.generator.system.entity.Institute;
import com.seating.plan.generator.system.repo.CityRepo;
import com.seating.plan.generator.system.req.web.view.InstituteReqView;

@Component
public class InstituteViewToEntityConvertor {
	@Autowired
	private CityRepo cityRepo;
	private static final Logger logger = LoggerFactory.getLogger(InstituteViewToEntityConvertor.class);

	public Institute convert(InstituteReqView source) throws Exception {
		Institute destination = new Institute();

		// logger.info("source cityId={} ",source.getCityId());
		//// System.out.println("Institute is" + institute.getId());

		// City city = cityRepo.findOne(source.getCityId());
		//
		// logger.info("destination city-Name={} ",city.getName());
		// System.out.println("Institute is" + city.getName());
		// destination.setId(source.getCityId());
		return destination;

	}
}
