package com.course.microservice.api.server;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.request.PerformanceAppraisalRequest;
import com.course.microservice.api.response.PlainMessage;
import com.course.microservice.command.response.PerformanceAppraisalApprovalResponse;
import com.course.microservice.command.service.CqrsPerformanceAppraisalService;
import com.course.microservice.entity.PerformanceAppraisalStatus;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/cqrs/appraisal")
public class CqrsPerformanceAppraisalApi {

	@Autowired
	private CqrsPerformanceAppraisalService service;

	@PostMapping(value = "/approval/{appraisal_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PerformanceAppraisalApprovalResponse> approvePerformanceAppraisal(
			@PathVariable(name = "appraisal_id", required = true) String appraisalId)
			throws NoSuchElementException, JsonProcessingException {
		service.approvePerformanceAppraisal(appraisalId);

		var responseBody = new PerformanceAppraisalApprovalResponse();
		responseBody.setAppraisalId(appraisalId);
		responseBody.setStatus(PerformanceAppraisalStatus.APPROVAL_ON_PROGRESS.toString());

		return ResponseEntity.ok().body(responseBody);
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlainMessage> createPerformanceAppraisal(
			@RequestBody(required = true) PerformanceAppraisalRequest requestBody) throws JsonProcessingException {
		var savedAppraisal = service.createPerformanceAppraisal(requestBody.getEmployeeId(), requestBody.getGrade(),
				requestBody.getScore());

		return ResponseEntity.ok().body(new PlainMessage(savedAppraisal.getAppraisalId().toString()));
	}

}
