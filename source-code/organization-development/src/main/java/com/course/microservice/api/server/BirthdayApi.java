package com.course.microservice.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.response.PlainMessage;
import com.course.microservice.command.service.BirthdayService;

@RestController
@RequestMapping("/api/birthday")
public class BirthdayApi {

	@Autowired
	private BirthdayService birthdayService;

	@GetMapping(value = "/{employee_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlainMessage> happyBirthday(@PathVariable(name = "employee_id") String employeeId)
			throws InterruptedException {
		var birthdayMessage = birthdayService.happyBirthday(employeeId);
		var responseBody = new PlainMessage("Message from birthdayService is : [" + birthdayMessage + "]");

		return ResponseEntity.ok().body(responseBody);
	}

}
