package com.course.microservice.broker.listener.cqrs01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.BonusPaidMessage;
import com.course.microservice.broker.message.BonusRecordedMessage;
import com.course.microservice.command.service.CqrsBonusRecordService;

@Component
public class CqrsBonusPaidListener {

	private static final Logger LOG = LoggerFactory.getLogger(CqrsBonusPaidListener.class);

	@Autowired
	private CqrsBonusRecordService service;

	@KafkaListener(topics = "t.cqrs01.payrollcompensation")
	@SendTo(value = "t.cqrs01.organizationdevelopment")
	public BonusRecordedMessage listenBonusPaid(BonusPaidMessage message) throws InterruptedException {
		LOG.debug("[CQRS-01] Listening bonus paid message for appraisal {}", message.getAppraisalId());

		service.simulateRecordBonus(message);
		var bonusRecordedMessage = service.convertToBonusRecordedMessage(message);

		// simulating slight delay to keep publish order of message
		Thread.sleep(50);

		LOG.debug("[CQRS-01] Publishing to next topic in saga for appraisal {}", bonusRecordedMessage.getAppraisalId());
		return bonusRecordedMessage;
	}

}
