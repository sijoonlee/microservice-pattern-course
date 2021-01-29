package com.course.microservice.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.api.client.IndustrialRelationClient;

@Service
public class PromotionService {

	@Autowired
	private IndustrialRelationClient industrialRelationClient;

	public String promoteSomeone(String employeeId) {
		var responseFromIndustrial = industrialRelationClient.congratulate(employeeId, "BIG PROMOTION");

		return responseFromIndustrial.getBody().getMessage();
	}

}
