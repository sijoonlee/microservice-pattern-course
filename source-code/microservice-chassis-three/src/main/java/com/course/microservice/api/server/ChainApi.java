package com.course.microservice.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.client.BirthdayClient;
import com.course.microservice.api.response.PlainMessage;

@RestController
@RequestMapping("/api")
public class ChainApi {

	@Autowired
	private BirthdayClient birthdayClient;

	@GetMapping("/chain/{employee_id}")
	public ResponseEntity<PlainMessage> chainApi(@PathVariable(name = "employee_id") String employeeId)
			throws InterruptedException {
		Thread.sleep(1000);
		return birthdayClient.happyBirthday(employeeId);
	}

}
