package com.course.microservice.broker.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.PerformanceAppraisalApprovedMessage;

@Component
public class CqrsPerformanceAppraisalPublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void publishForCqrsSaga(PerformanceAppraisalApprovedMessage appraisalApprovedMessage) {
		kafkaTemplate.send("t.cqrs01.performancemanagement", appraisalApprovedMessage);
	}

}
