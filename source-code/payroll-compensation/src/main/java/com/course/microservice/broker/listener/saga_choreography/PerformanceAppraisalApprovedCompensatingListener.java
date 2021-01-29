package com.course.microservice.broker.listener.saga_choreography;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.BonusPaidMessage;
import com.course.microservice.broker.message.PerformanceAppraisalApprovedMessage;

@Component
public class PerformanceAppraisalApprovedCompensatingListener {

	private static final Logger LOG = LoggerFactory.getLogger(PerformanceAppraisalApprovedCompensatingListener.class);

	@KafkaListener(topics = "t.saga02.performancemanagement")
	@SendTo(value = "t.saga02.payrollcompensation")
	public BonusPaidMessage listenPerformanceAppraisal(PerformanceAppraisalApprovedMessage message)
			throws InterruptedException {
		LOG.debug("[Choreography-Compensating Saga] Listening performance approved message for appraisal {}",
				message.getAppraisalId());

		// ...
		// do business logic here for performance appraisal message,
		// e.g. process bonus payment to bank account
		// etc
		// ...

		// simulate process
		Thread.sleep(3000);

		// send the message to next topic in saga
		// bonus amount is negative to trigger error
		var bonusPaidMessage = new BonusPaidMessage();
		bonusPaidMessage.setAppraisalId(message.getAppraisalId());
		bonusPaidMessage.setEmployeeId(message.getEmployeeId());
		bonusPaidMessage.setBonusAmount(-1000);
		bonusPaidMessage.setBonusPaidDateTime(LocalDateTime.of(LocalDate.of(2021, 06, 30), LocalTime.now()));
		bonusPaidMessage.setPaidToBankAccount("9063772801");

		LOG.debug("[Choreography-Compensating Saga] Publishing to next topic in saga for appraisal {}",
				bonusPaidMessage.getAppraisalId());
		return bonusPaidMessage;
	}

}
