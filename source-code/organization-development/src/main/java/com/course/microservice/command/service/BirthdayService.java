package com.course.microservice.command.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.api.client.IndustrialRelationClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BirthdayService {

	private static final Logger LOG = LoggerFactory.getLogger(BirthdayService.class);

	@Autowired
	private IndustrialRelationClient industrialRelationClient;

	public String defaultBirthdayMessage(String employeeId, Throwable t) {
		LOG.debug("on defaultBirthdayMessage() for employee {}", employeeId);

		return "Sorry ".concat(employeeId)
				.concat(", we cannot give official birthday message right now. " + "Please try again in few moments.");
	}

	@CircuitBreaker(name = "birthdayCircuitBreaker", fallbackMethod = "defaultBirthdayMessage")
	public String happyBirthday(String employeeId) throws InterruptedException {
		LOG.debug("on happyBirthday() for employee {}", employeeId);

		// delay for a while
		Thread.sleep(1000);
		var responseFromIndustrial = industrialRelationClient.congratulate(employeeId, "BIRTHDAY");

		return responseFromIndustrial.getBody().getMessage();
	}

}
