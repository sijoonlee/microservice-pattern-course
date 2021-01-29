package com.course.microservice.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.request.EmployeeAssignmentPollingRequest;
import com.course.microservice.api.response.PlainMessage;
import com.course.microservice.command.service.EmployeeAssignmentPollingService;
import com.course.microservice.entity.EmployeeAssignment;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/assignment/polling")
public class EmployeeAssignmentPollingApi {

	@Autowired
	private EmployeeAssignmentPollingService service;

	@DeleteMapping(value = "/{assignment_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlainMessage> deleteAssignment(
			@PathVariable(name = "assignment_id", required = true) long assignmentPollingId)
			throws JsonProcessingException {
		service.delete(assignmentPollingId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new PlainMessage("Deleted : " + assignmentPollingId));
	}

	@PostMapping(value = { "",
			"/" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlainMessage> insertAssignment(
			@RequestBody(required = true) EmployeeAssignmentPollingRequest requestBody) throws JsonProcessingException {
		var entity = new EmployeeAssignment();
		entity.setDateEnd(requestBody.getDateEnd());
		entity.setDateStart(requestBody.getDateStart());
		entity.setEmployeeId(requestBody.getEmployeeId());
		entity.setPosition(requestBody.getPosition());

		var newAssignmentId = service.insert(entity);

		return ResponseEntity.status(HttpStatus.CREATED).body(new PlainMessage("Created : " + newAssignmentId));
	}

}
