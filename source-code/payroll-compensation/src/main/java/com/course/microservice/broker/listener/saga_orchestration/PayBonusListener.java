package com.course.microservice.broker.listener.saga_orchestration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.BonusPaidMessage;
import com.course.microservice.broker.message.PayBonusMessage;

@Component
public class PayBonusListener {

	private static final Logger LOG = LoggerFactory.getLogger(PayBonusListener.class);

	@KafkaListener(topics = "t.saga03.payrollcompensation.request")
	@SendTo(value = "t.saga03.payrollcompensation.response")
	public BonusPaidMessage listenPayBonus(PayBonusMessage message) throws InterruptedException {
		LOG.debug("[Orchestration-Saga] Listening pay bonus message for appraisal {}", message.getAppraisalId());

		// ...
		// do business logic here for pay bonus message,
		// e.g. process bonus payment to bank account
		// etc
		// ...

		// simulate process
		Thread.sleep(3000);

		// send the message to response topic in saga
		var bonusPaidMessage = new BonusPaidMessage();
		bonusPaidMessage.setAppraisalId(message.getAppraisalId());
		bonusPaidMessage.setEmployeeId(message.getEmployeeId());
		bonusPaidMessage.setBonusAmount(3500);
		bonusPaidMessage.setBonusPaidDateTime(LocalDateTime.of(LocalDate.of(2021, 06, 30), LocalTime.now()));
		bonusPaidMessage.setPaidToBankAccount("5799206001");

		LOG.debug("[Orchestration-Saga] Publishing to bonus payment response topic for appraisal {}",
				bonusPaidMessage.getAppraisalId());
		return bonusPaidMessage;
	}

}
