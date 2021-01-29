package com.course.microservice.api.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.request.EmployeeRequest;
import com.course.microservice.api.response.EmployeeResponse;
import com.course.microservice.command.service.EmployeeService;
import com.course.microservice.query.service.EmployeeQueryService;

/**
 * Sample employee API
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeApi {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeApi.class);

	@Autowired
	private EmployeeService commandService;

	@Autowired
	private EmployeeQueryService queryService;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createEmployee(@RequestBody EmployeeRequest requestBody) {
		LOG.debug("Processing employee reccomended by : {}", requestBody.getReccomendedBy());

		if (commandService.createEmployee(requestBody.getEmployee())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE.toString());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.FALSE.toString());
		}
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeResponse> getByAmount(@RequestParam(required = false, defaultValue = "0") int min,
			@RequestParam(required = true) int max) {
		var responseBody = new EmployeeResponse(queryService.getEmployeesByAge(min, max));
		return ResponseEntity.ok().body(responseBody);
	}
}
