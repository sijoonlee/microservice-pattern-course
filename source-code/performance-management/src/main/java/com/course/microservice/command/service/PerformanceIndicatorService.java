package com.course.microservice.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.command.action.PerformanceIndicatorAction;

@Service
public class PerformanceIndicatorService {

	@Autowired
	private PerformanceIndicatorAction action;

	public void createDummyData(int dataSize) {
		action.deleteAll();
		action.createDummyData(dataSize);
	}

}
