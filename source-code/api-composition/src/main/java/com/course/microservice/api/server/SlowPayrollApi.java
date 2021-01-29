package com.course.microservice.api.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.client.SlowPayrollClient;

@RestController
@RequestMapping("/api/payroll")
public class SlowPayrollApi {

	@Autowired
	private SlowPayrollClient slowPayrollClient;

	private static final Logger LOG = LoggerFactory.getLogger(SlowPayrollApi.class);

	@GetMapping(value = "/slow", produces = MediaType.TEXT_PLAIN_VALUE)
	@Cacheable(cacheNames = "payrollCache")
	public ResponseEntity<String> slowPayrollApi(
			@RequestParam(value = "employee_id", required = true) String employeeId) {
		LOG.debug("Processing slow API call");
		return slowPayrollClient.slowCalculation(employeeId);
	}

}
