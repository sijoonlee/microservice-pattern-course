package com.course.microservice.api.server;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculation")
public class SlowCalculationApi {

	@GetMapping(value = "/slow", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> slowCalculation(
			@RequestParam(value = "employee_id", required = true) String employeeId) throws InterruptedException {
		// simulate slow process
		Thread.sleep(ThreadLocalRandom.current().nextLong(2000, 4000));
		return ResponseEntity.ok().body("Slow calculation from payroll-compensation for employee : " + employeeId);
	}
	
}
