package com.course.microservice.query.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.entity.PerformanceBonus;
import com.course.microservice.query.action.PerformanceBonusQueryAction;

@Service
public class PerformanceBonusQueryService {

	@Autowired
	private PerformanceBonusQueryAction action;

	public PerformanceBonus getPerformanceBonusById(String appraisalId) {
		return action.getPerformanceBonusById(appraisalId);
	}

}
