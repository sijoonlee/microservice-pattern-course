package com.course.microservice.broker.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.OutboxPollingMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class EmployeeAssignmentPollingListener {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeAssignmentPollingListener.class);

	@KafkaListener(topics = "t.outboxpolling")
	public void listenEmployeeAssignment(OutboxPollingMessage message)
			throws JsonMappingException, JsonProcessingException {
		LOG.info("Outbox message is : {}", message);
	}

}
