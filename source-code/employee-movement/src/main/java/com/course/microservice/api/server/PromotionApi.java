package com.course.microservice.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.response.PlainMessage;
import com.course.microservice.command.service.PromotionService;

@RestController
@RequestMapping("/api/promotion")
public class PromotionApi {

	@Autowired
	private PromotionService promotionService;

	@GetMapping(value = "/{employee_id}")
	public ResponseEntity<PlainMessage> promoteSomeone(@PathVariable(name = "employee_id") String employeeId) {
		var promotionMessage = promotionService.promoteSomeone(employeeId);
		var responseBody = new PlainMessage("Message from promotionService is : [" + promotionMessage + "]");

		return ResponseEntity.ok().body(responseBody);
	}

}
