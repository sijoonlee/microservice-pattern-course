package com.course.microservice.api.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.response.PlainMessage;

@RestController
@RequestMapping("/api/congratulation")
public class CongratulationApi {

	@GetMapping(value = "/{employee_id}")
	public ResponseEntity<PlainMessage> congratulate(
			@PathVariable(name = "employee_id", required = true) String employeeId,
			@RequestParam(name = "event", defaultValue = "SECRET EVENT") String event) {
		var message = "Congratulations ".concat(employeeId).concat(" for your ").concat(event)
				.concat(". We are happy for you!");
		var responseBody = new PlainMessage(message);

		return ResponseEntity.ok().body(responseBody);
	}

}
