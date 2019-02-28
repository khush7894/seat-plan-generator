package com.seating.plan.generator.system.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConverterConfig {

	@Autowired
	@Qualifier("modelMapperWithConverter")
	private ModelMapper modelMapper;

	
	
}
