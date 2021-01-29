package com.course.microservice.api.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.course.microservice.api.response.PlainMessage;

@Component
public class IndustrialRelationCircuitBreaker implements IndustrialRelationClient {

	@Override
	public ResponseEntity<PlainMessage> congratulate(String employeeId, String event) {
		var defaultMessage = "Sorry ".concat(employeeId)
				.concat(", we cannot give official congratulation message right now. " + "Please try again later.");
		var responseBody = new PlainMessage(defaultMessage);

		return ResponseEntity.ok().body(responseBody);
	}

}
