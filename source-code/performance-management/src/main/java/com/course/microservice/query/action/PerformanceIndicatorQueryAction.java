package com.course.microservice.query.action;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.course.microservice.entity.PerformanceIndicator;
import com.course.microservice.repository.PerformanceIndicatorRepository;

@Component
public class PerformanceIndicatorQueryAction {

	@Autowired
	private PerformanceIndicatorRepository repository;

	public long getDataCount() {
		return repository.count();
	}

	public List<PerformanceIndicator> getDataWithPagination(int page, int size) {
		return repository.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());
	}

}
