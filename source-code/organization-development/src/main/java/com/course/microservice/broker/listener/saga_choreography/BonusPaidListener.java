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
import com.course.microservice.broker.message.BonusRecordedMessage;

@Component
public class BonusPaidListener {

	private static final Logger LOG = LoggerFactory.getLogger(BonusPaidListener.class);

	@KafkaListener(topics = "t.saga01.payrollcompensation")
	@SendTo(value = "t.saga01.organizationdevelopment")
	public BonusRecordedMessage listenBonusPaid(BonusPaidMessage message)
			throws InterruptedException {
		LOG.debug("[Choreography-Saga] Listening bonus paid message for appraisal {}", message.getAppraisalId());

		// ...
		// do business logic here for bonus paid message,
		// e.g. record bonus history for that employee
		// ...

		// simulate process
		Thread.sleep(3000);

		// send the message to next topic in saga
		var bonusRecordedMessage = new BonusRecordedMessage();
		bonusRecordedMessage.setAppraisalId(message.getAppraisalId());
		bonusRecordedMessage.setEmployeeId(message.getEmployeeId());
		bonusRecordedMessage.setBonusRecordedDateTime(LocalDateTime.of(LocalDate.of(2021, 06, 30), LocalTime.now()));

		LOG.debug("[Choreography-Saga] Publishing to next topic in saga for appraisal {}",
				bonusRecordedMessage.getAppraisalId());
		return bonusRecordedMessage;
	}

}
