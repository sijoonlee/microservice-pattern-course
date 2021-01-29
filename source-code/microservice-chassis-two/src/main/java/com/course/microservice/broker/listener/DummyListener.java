package com.course.microservice.broker.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

import com.course.microservice.broker.message.DummyBrokerMessageOne;
import com.course.microservice.broker.message.DummyBrokerMessageTwo;

@KafkaListener(topics = "t.chassistwo")
public class DummyListener {

	private static final Logger LOG = LoggerFactory.getLogger(DummyListener.class);

	@KafkaHandler
	public void handleMessageOne(DummyBrokerMessageOne message) {
		LOG.info(message.toString());
	}

	@KafkaHandler
	public void handleMessageTwo(DummyBrokerMessageTwo message) {
		LOG.info(message.toString());
	}

}
