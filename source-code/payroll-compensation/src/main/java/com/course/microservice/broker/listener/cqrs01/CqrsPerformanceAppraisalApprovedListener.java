package com.course.microservice.broker.listener.cqrs01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.BonusPaidMessage;
import com.course.microservice.broker.message.PerformanceAppraisalApprovedMessage;
import com.course.microservice.command.service.CqrsPerformanceBonusService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class CqrsPerformanceAppraisalApprovedListener {

	private static final Logger LOG = LoggerFactory.getLogger(CqrsPerformanceAppraisalApprovedListener.class);

	@Autowired
	private CqrsPerformanceBonusService service;

	@KafkaListener(topics = "t.cqrs01.performancemanagement")
	@SendTo(value = "t.cqrs01.payrollcompensation")
	public BonusPaidMessage listenPerformanceAppraisal(PerformanceAppraisalApprovedMessage message)
			throws InterruptedException, JsonProcessingException {
		LOG.debug("[CQRS-01] Listening performance approved message for appraisal {}", message.getAppraisalId());

		var paidBonus = service.payPerformanceBonus(message);
		var bonusPaidMessage = service.convertToBonusPaidMessage(message, paidBonus);

		// simulating slight delay to keep publish order of message
		Thread.sleep(50);

		LOG.debug("[CQRS-01] Publishing to next topic in saga for appraisal {}", bonusPaidMessage.getAppraisalId());
		return bonusPaidMessage;
	}

}
