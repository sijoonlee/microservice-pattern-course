package com.course.microservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ @PropertySource(factory = YamlPropertySourceFactory.class, value = { "classpath:microservice.yml",
		"file:/conf/microservice.yml", "file:c:/conf/microservice.yml" }, ignoreResourceNotFound = true) })
public class ExternalConfig {
}
