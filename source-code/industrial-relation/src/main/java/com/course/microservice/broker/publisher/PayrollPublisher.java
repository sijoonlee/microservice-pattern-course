package com.course.microservice.broker.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.microservice.broker.message.MasterPayrollMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class PayrollPublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void publishMasterPayrollMessage(MasterPayrollMessage message) throws JsonProcessingException {
		kafkaTemplate.send("t.masterpayroll", message);
	}

}
