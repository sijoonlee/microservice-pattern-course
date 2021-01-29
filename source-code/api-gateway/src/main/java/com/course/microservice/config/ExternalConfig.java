package com.course.microservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ @PropertySource(factory = YamlPropertySourceFactory.class, value = { "classpath:gateway.yml",
		"file:/conf/gateway.yml", "file:c:/conf/gateway.yml" }, ignoreResourceNotFound = true) })
public class ExternalConfig {
}
