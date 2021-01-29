package com.course.microservice.query.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.api.client.PerformanceAppraisalClient;
import com.course.microservice.api.client.PerformanceBonusClient;
import com.course.microservice.api.response.PerformanceAppraisal;
import com.course.microservice.api.response.PerformanceBonus;

@Component
public class PerformanceHistoryQueryAction {

	@Autowired
	private PerformanceAppraisalClient performanceAppraisalClient;

	@Autowired
	private PerformanceBonusClient performanceBonusClient;

	public List<PerformanceAppraisal> getAllPerformanceAppraisal() {
		return performanceAppraisalClient.getAppraisals().getBody();
	}

	public PerformanceBonus getBonusForAppraisal(String appraisalId) {
		return performanceBonusClient.getPerformanceBonus(appraisalId).getBody();
	}

}
