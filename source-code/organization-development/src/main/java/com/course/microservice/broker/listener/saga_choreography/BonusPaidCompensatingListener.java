package com.course.microservice.broker.listener.saga_choreography;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.BonusPaidMessage;
import com.course.microservice.broker.message.RecordBonusErrorMessage;
import com.course.microservice.broker.message.BonusRecordedMessage;

@Component
public class BonusPaidCompensatingListener {

	private static final Logger LOG = LoggerFactory.getLogger(BonusPaidCompensatingListener.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@KafkaListener(topics = "t.saga02.payrollcompensation")
	public void listenBonusPaid(BonusPaidMessage message)
			throws InterruptedException {
		LOG.debug("[Choreography-Compensating Saga] Listening bonus paid message for appraisal {}", message.getAppraisalId());

		// ...
		// do business logic here for bonus paid message,
		// e.g. record bonus history for that employee
		// ...

		// simulate process error, e.g. cannot record bonus
		if (message.getBonusAmount() < 0) {
			// publish to kafka & terminate process
			var errorMessage = "Error record bonus, due to invalid bonus amount : " + message.getBonusAmount();
			var recordBonusErrorMessage = new RecordBonusErrorMessage();
			recordBonusErrorMessage.setAppraisalId(message.getAppraisalId());
			recordBonusErrorMessage.setErrorMessage(errorMessage);
			recordBonusErrorMessage.setBonusAmount(message.getBonusAmount());

			LOG.error("[Choreography-Compensating Saga] {}", errorMessage);

			kafkaTemplate.send("t.saga02.organizationdevelopment", recordBonusErrorMessage);
			
			throw new IllegalArgumentException(errorMessage);
		}

		// simulate process
		Thread.sleep(3000);

		// send the message to next topic in saga
		var bonusRecordedMessage = new BonusRecordedMessage();
		bonusRecordedMessage.setAppraisalId(message.getAppraisalId());
		bonusRecordedMessage.setEmployeeId(message.getEmployeeId());
		bonusRecordedMessage.setBonusRecordedDateTime(LocalDateTime.of(LocalDate.of(2021, 06, 30), LocalTime.now()));

		LOG.debug("[Choreography-Compensating Saga] Publishing to next topic in saga for appraisal {}",
				bonusRecordedMessage.getAppraisalId());
		kafkaTemplate.send("t.saga02.organizationdevelopment", bonusRecordedMessage);
	}

}
