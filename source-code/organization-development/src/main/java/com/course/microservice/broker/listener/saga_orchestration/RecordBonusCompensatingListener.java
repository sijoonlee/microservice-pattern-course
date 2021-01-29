package com.course.microservice.broker.listener.saga_orchestration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.RecordBonusErrorMessage;
import com.course.microservice.broker.message.BonusRecordedMessage;
import com.course.microservice.broker.message.RecordBonusMessage;

@Component
public class RecordBonusCompensatingListener {

	private static final Logger LOG = LoggerFactory.getLogger(RecordBonusCompensatingListener.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@KafkaListener(topics = "t.saga04.organizationdevelopment.request")
	@SendTo(value = "t.saga04.organizationdevelopment.response")
	public BonusRecordedMessage listenRecordBonus(RecordBonusMessage message) throws InterruptedException {
		LOG.debug("[Orchestration-Compensating Saga] Listening record bonus message for appraisal {}",
				message.getAppraisalId());

		// ...
		// do business logic here for record bonus message,
		// e.g. record bonus history for that employee
		// ...

		// simulate error
		if (message.getBonusAmount() < 0) {
			// publish to kafka & terminate process
			var errorMessage = "Error record bonus, due to invalid bonus amount : " + message.getBonusAmount();
			var recordBonusErrorMessage = new RecordBonusErrorMessage();
			recordBonusErrorMessage.setAppraisalId(message.getAppraisalId());
			recordBonusErrorMessage.setErrorMessage(errorMessage);
			recordBonusErrorMessage.setBonusAmount(message.getBonusAmount());

			LOG.error("[Orchestration-Compensating Saga] {}", errorMessage);

			kafkaTemplate.send("t.saga04.organizationdevelopment.response", recordBonusErrorMessage);
			
			throw new IllegalArgumentException(errorMessage);
		}
		// end simulate error

		// simulate process
		Thread.sleep(3000);

		// send the message to next topic in saga
		var bonusRecordedMessage = new BonusRecordedMessage();
		bonusRecordedMessage.setAppraisalId(message.getAppraisalId());
		bonusRecordedMessage.setEmployeeId(message.getEmployeeId());
		bonusRecordedMessage.setBonusRecordedDateTime(LocalDateTime.of(LocalDate.of(2021, 06, 30), LocalTime.now()));

		LOG.debug("[Orchestration-Compensating Saga] Publishing to record bonus response topic for appraisal {}",
				bonusRecordedMessage.getAppraisalId());
		return bonusRecordedMessage;
	}

}
