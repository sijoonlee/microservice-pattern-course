package com.course.microservice.query.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.entity.PerformanceIndicator;
import com.course.microservice.query.action.PerformanceIndicatorQueryAction;

@Service
public class PerformanceIndicatorQueryService {

	@Autowired
	private PerformanceIndicatorQueryAction action;

	public long getDataCount() {
		return action.getDataCount();
	}

	public List<PerformanceIndicator> getDataWithPagination(int page, int size) {
		return action.getDataWithPagination(page, size);
	}

}
