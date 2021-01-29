package com.course.microservice.command.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.api.client.IndustrialRelationClient;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class WeddingService {

	private static final Logger LOG = LoggerFactory.getLogger(WeddingService.class);
	
	@Autowired
	private IndustrialRelationClient industrialRelationClient;

	public String defaultWeddingMessage(String employeeId, Throwable t) {
		LOG.debug("on defaultWeddingMessage() for employee {}", employeeId);

		return "Sorry ".concat(employeeId)
				.concat(", we cannot give official wedding message right now. " + "We happy for both of you.");
	}

	@Retry(name = "industrialRelationClient", fallbackMethod = "defaultWeddingMessage")
	public String happyWedding(String employeeId) {
		LOG.debug("on happyWedding() for employee {}", employeeId);

		var responseFromIndustrial = industrialRelationClient.congratulate(employeeId, "WEDDING");

		return responseFromIndustrial.getBody().getMessage();
	}

}
