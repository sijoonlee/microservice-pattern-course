package com.course.microservice.query.action;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.entity.PerformanceBonus;
import com.course.microservice.repository.PerformanceBonusRepository;

@Component
public class PerformanceBonusQueryAction {

	@Autowired
	private PerformanceBonusRepository repository;

	public PerformanceBonus getPerformanceBonusById(String appraisalId) {
		return repository.findById(UUID.fromString(appraisalId)).get();
	}

}
