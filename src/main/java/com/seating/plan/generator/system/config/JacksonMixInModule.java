package com.seating.plan.generator.system.config;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixInModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	@Override
	public void setupModule(SetupContext context) {

		// context.setMixInAnnotations(User.class, UserIgnore.class);

	}

}
