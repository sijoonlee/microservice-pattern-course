package com.course.microservice.api.server;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApi {

	@GetMapping(value = "/ping", produces = MediaType.TEXT_PLAIN_VALUE)
	public String healthCheck() {
		return "UP";
	}

}
