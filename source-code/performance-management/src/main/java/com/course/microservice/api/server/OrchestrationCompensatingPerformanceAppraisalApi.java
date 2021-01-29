package com.course.microservice.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.command.response.PerformanceAppraisalApprovalResponse;
import com.course.microservice.command.service.OrchestrationCompensatingPerformanceAppraisalSaga;
import com.course.microservice.entity.PerformanceAppraisalStatus;

@RestController
@RequestMapping("/api/saga/orchestration/appraisal")
public class OrchestrationCompensatingPerformanceAppraisalApi {

	@Autowired
	private OrchestrationCompensatingPerformanceAppraisalSaga saga;

	@PostMapping(value = "/approval_compensation/{appraisal_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PerformanceAppraisalApprovalResponse> approveCompensatingPerformanceAppraisal(
			@PathVariable(name = "appraisal_id", required = true) String appraisalId) {
		saga.approvePerformanceAppraisal(appraisalId);

		var responseBody = new PerformanceAppraisalApprovalResponse();
		responseBody.setAppraisalId(appraisalId);
		responseBody.setStatus(PerformanceAppraisalStatus.APPROVAL_ON_PROGRESS.toString());

		return ResponseEntity.ok().body(responseBody);
	}

}
