package com.course.microservice.broker.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.microservice.broker.message.DummyBrokerMessageOne;
import com.course.microservice.broker.message.DummyBrokerMessageTwo;

@Service
public class DummyPublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void publishMessageOne(DummyBrokerMessageOne message) {
		kafkaTemplate.send("t.chassistwo", message);
	}

	public void publishMessageTwo(DummyBrokerMessageTwo message) {
		kafkaTemplate.send("t.chassistwo", message);
	}

}
