package com.course.microservice.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.course.microservice.api.response.PlainMessage;

@FeignClient(name = "industrial-relation", fallback = IndustrialRelationCircuitBreaker.class, url = "http://localhost:8882")
public interface IndustrialRelationClient {

	@GetMapping(value = "/api/congratulation/{employee_id}")
	public ResponseEntity<PlainMessage> congratulate(@PathVariable(name = "employee_id") String employeeId,
			@RequestParam(name = "event") String event);

}
