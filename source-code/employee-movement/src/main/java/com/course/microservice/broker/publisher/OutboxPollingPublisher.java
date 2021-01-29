package com.course.microservice.broker.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.microservice.broker.message.OutboxPollingMessage;

@Service
public class OutboxPollingPublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void publish(OutboxPollingMessage outbox) {
		kafkaTemplate.send("t.outboxpolling", Long.toString(outbox.getId()), outbox);
	}

}
