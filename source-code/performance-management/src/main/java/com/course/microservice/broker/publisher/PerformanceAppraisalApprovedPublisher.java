package com.course.microservice.broker.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.PerformanceAppraisalApprovedMessage;

@Component
public class PerformanceAppraisalApprovedPublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void publishForCoreographyCompensatingSaga(PerformanceAppraisalApprovedMessage appraisalApprovedMessage) {
		kafkaTemplate.send("t.saga02.performancemanagement", appraisalApprovedMessage);
	}

	public void publishForCoreographySaga(PerformanceAppraisalApprovedMessage appraisalApprovedMessage) {
		kafkaTemplate.send("t.saga01.performancemanagement", appraisalApprovedMessage);
	}

}
