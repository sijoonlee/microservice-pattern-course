package com.course.microservice.command.action;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.entity.PerformanceIndicator;
import com.course.microservice.repository.PerformanceIndicatorRepository;

@Component
public class PerformanceIndicatorAction {

	@Autowired
	private PerformanceIndicatorRepository repository;

	public void createDummyData(int dataSize) {
		var dummies = new ArrayList<PerformanceIndicator>();

		for (int i = 1; i <= dataSize; i++) {
			var performanceIndicator = new PerformanceIndicator();
			performanceIndicator.setName("Indicator-" + i);
			performanceIndicator.setDescription("Performance indicator description " + i);

			dummies.add(performanceIndicator);
		}

		repository.saveAll(dummies);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

}
