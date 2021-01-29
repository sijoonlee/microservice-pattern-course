package com.course.microservice.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.response.PlainMessage;
import com.course.microservice.command.service.WeddingService;

@RestController
@RequestMapping("/api/wedding")
public class WeddingApi {

	@Autowired
	private WeddingService weddingService;

	@GetMapping(value = "/{employee_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlainMessage> happyWedding(@PathVariable(name = "employee_id") String employeeId) {
		var weddingMessage = weddingService.happyWedding(employeeId);
		var responseBody = new PlainMessage("Message from weddingService is : [" + weddingMessage + "]");

		return ResponseEntity.ok().body(responseBody);
	}

}
