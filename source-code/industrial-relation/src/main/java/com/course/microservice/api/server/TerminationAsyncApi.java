package com.course.microservice.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.request.TerminationApprovalRequest;
import com.course.microservice.api.response.TerminationApprovalResponse;
import com.course.microservice.command.service.TerminationAsyncService;

@RestController
@RequestMapping("/api/termination_async")
public class TerminationAsyncApi {

	@Autowired
	private TerminationAsyncService terminationAsyncService;

	@PostMapping(value = "/{employee_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TerminationApprovalResponse> approveEmployeeTermination(
			@PathVariable(name = "employee_id", required = true) String employeeId,
			@RequestBody(required = true) TerminationApprovalRequest requestBody) {

		terminationAsyncService.approveEmployeeTermination(employeeId, requestBody.getTerminationDate());

		// dummy response
		var responseBody = new TerminationApprovalResponse();
		responseBody.setEmployeeId(employeeId);
		responseBody.setMessage("Done publishing termination_async approval");

		return ResponseEntity.ok().body(responseBody);
	}

}
