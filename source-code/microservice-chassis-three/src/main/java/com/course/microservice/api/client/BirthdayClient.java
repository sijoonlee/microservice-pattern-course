package com.course.microservice.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.course.microservice.api.response.PlainMessage;

@FeignClient(name = "organization-development", qualifier = "birthdayClient")
public interface BirthdayClient {

	@GetMapping(value = "/api/birthday/{employee_id}")
	ResponseEntity<PlainMessage> happyBirthday(@PathVariable(name = "employee_id") String employeeId);

}
