package com.course.microservice.broker.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.DebeziumEmployeeAssignment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmployeeAssignmentListener {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeAssignmentListener.class);

	@Autowired
	private ObjectMapper objectMapper;

	@KafkaListener(topics = "t.dbserver01.employee_movement.employee_assignment", containerFactory = "stringDeserializerContainerFactory")
	public void listenEmployeeAssignment(String message) throws JsonMappingException, JsonProcessingException {
		var debeziumEmployeeAssignment = objectMapper.readValue(message, DebeziumEmployeeAssignment.class);

		LOG.info("Payload from debezium is : {}", debeziumEmployeeAssignment.getPayload());
	}

}
