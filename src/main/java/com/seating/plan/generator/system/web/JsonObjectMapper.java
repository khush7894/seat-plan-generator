package com.seating.plan.generator.system.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.seating.plan.generator.system.config.JacksonMixInModule;

public class JsonObjectMapper extends ObjectMapper {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public JsonObjectMapper() {
		JsonSerializer<Double> doubleSerializer = new JsonSerializer<Double>() {

			@Override
			public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {

				jgen.writeNumber(value);

			}

		};

		Map<Class<?>, JsonSerializer<?>> mapping = new HashMap<Class<?>, JsonSerializer<?>>();
		mapping.put(double.class, doubleSerializer);

		this.registerModule(new JodaModule());

		this.registerModule(new JacksonMixInModule());

		Hibernate4Module module = new Hibernate4Module();
		module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
		this.registerModule(module);

		this.enable(SerializationFeature.INDENT_OUTPUT);
	}
}
