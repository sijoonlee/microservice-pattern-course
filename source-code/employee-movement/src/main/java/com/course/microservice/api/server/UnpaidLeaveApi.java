package com.course.microservice.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.request.UnpaidLeaveApprovalRequest;
import com.course.microservice.api.response.UnpaidLeaveApprovalResponse;
import com.course.microservice.command.service.UnpaidLeaveService;

@RestController
@RequestMapping("/api/unpaid_leave")
public class UnpaidLeaveApi {

	@Autowired
	private UnpaidLeaveService unpaidLeaveService;

	@PostMapping(value = "/{employee_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UnpaidLeaveApprovalResponse> approveEmployeeUnpaidLeave(
			@PathVariable(name = "employee_id", required = true) String employeeId,
			@RequestBody(required = true) UnpaidLeaveApprovalRequest requestBody) {

		unpaidLeaveService.approveUnpaidLeave(employeeId, requestBody.getUnpaidLeaveDate());

		// dummy response
		var responseBody = new UnpaidLeaveApprovalResponse();
		responseBody.setEmployeeId(employeeId);
		responseBody.setMessage("Done simulating unpaid-leave approval");

		return ResponseEntity.ok().body(responseBody);
	}

}
