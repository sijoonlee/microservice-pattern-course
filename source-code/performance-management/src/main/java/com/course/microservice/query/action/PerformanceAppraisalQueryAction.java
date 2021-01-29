package com.course.microservice.query.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.entity.PerformanceAppraisal;
import com.course.microservice.repository.PerformanceAppraisalRepository;
import com.google.common.collect.Lists;

@Component
public class PerformanceAppraisalQueryAction {

	@Autowired
	private PerformanceAppraisalRepository repository;

	public List<PerformanceAppraisal> getAllPerformanceAppraisal() {
		return Lists.newArrayList(repository.findAll());
	}

}
