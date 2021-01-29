package com.course.microservice.query.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.entity.PerformanceAppraisal;
import com.course.microservice.query.action.PerformanceAppraisalQueryAction;

@Service
public class PerformanceAppraisalQueryService {

	@Autowired
	private PerformanceAppraisalQueryAction queryAction;

	public List<PerformanceAppraisal> getAllPerformanceAppraisal() {
		return queryAction.getAllPerformanceAppraisal();
	}

}
