package com.course.microservice.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	// springdoc config
	@Bean
	public OpenAPI customOpenAPI() {
		var servers = new ArrayList<Server>();
		servers.add(new Server().url("http://localhost:8004").description("Development server"));

		return new OpenAPI().components(new Components()).info(new Info()
				.description("<p>This is sample API documentation for microservice Architecture & Pattern course.</p>")
				.title("Microservice Architecture & Pattern").version("1.0.0")).servers(servers);
	}

}
