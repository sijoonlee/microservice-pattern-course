package com.course.microservice.broker.listener.saga_orchestration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.BonusRecordedMessage;
import com.course.microservice.broker.message.RecordBonusMessage;

@Component
public class RecordBonusListener {

	private static final Logger LOG = LoggerFactory.getLogger(RecordBonusListener.class);

	@KafkaListener(topics = "t.saga03.organizationdevelopment.request")
	@SendTo(value = "t.saga03.organizationdevelopment.response")
	public BonusRecordedMessage listenRecordBonus(RecordBonusMessage message) throws InterruptedException {
		LOG.debug("[Orchestration-Saga] Listening record bonus message for appraisal {}", message.getAppraisalId());

		// ...
		// do business logic here for record bonus message,
		// e.g. record bonus history for that employee
		// ...

		// simulate process
		Thread.sleep(3000);

		// send the message to next topic in saga
		var bonusRecordedMessage = new BonusRecordedMessage();
		bonusRecordedMessage.setAppraisalId(message.getAppraisalId());
		bonusRecordedMessage.setEmployeeId(message.getEmployeeId());
		bonusRecordedMessage.setBonusRecordedDateTime(LocalDateTime.of(LocalDate.of(2021, 06, 30), LocalTime.now()));

		LOG.debug("[Orchestration-Saga] Publishing to record bonus response topic for appraisal {}",
				bonusRecordedMessage.getAppraisalId());
		return bonusRecordedMessage;
	}

}
