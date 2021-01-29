package com.course.microservice.broker.listener.saga_orchestration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.BonusPaidMessage;
import com.course.microservice.broker.message.PayBonusMessage;
import com.course.microservice.broker.message.RecordBonusErrorMessage;

@Component
@KafkaListener(topics = "t.saga04.payrollcompensation.request")
public class PayBonusCompensatingListener {

	private static final Logger LOG = LoggerFactory.getLogger(PayBonusCompensatingListener.class);

	@KafkaHandler
	public void listenErrorPayBonus(RecordBonusErrorMessage message) throws InterruptedException {
		LOG.debug("[Orchestration-Compensating Saga] Listening bonus record error message for appraisal {}",
				message.getAppraisalId());

		// ...
		// Compensate previous transaction, e.g. debit bonus that already paid
		// (credited), with
		// same amount as wrong data
		// ...

		// simulate compensation
		Thread.sleep(3000);

		LOG.debug("[Orchestration-Compensating Saga] Compensate previous transaction, e.g. debit bonus "
				+ "that already paid (credited), with same amount as wrong data : " + message.getBonusAmount());
	}

	@KafkaHandler
	@SendTo(value = "t.saga04.payrollcompensation.response")
	public BonusPaidMessage listenPayBonus(PayBonusMessage message) throws InterruptedException {
		LOG.debug("[Orchestration-Compensating Saga] Listening pay bonus message for appraisal {}",
				message.getAppraisalId());

		// ...
		// do business logic here for pay bonus message,
		// e.g. process bonus payment to bank account,
		// etc
		// ...

		// simulate process
		Thread.sleep(3000);

		// send the message to next topic in saga
		var bonusPaidMessage = new BonusPaidMessage();
		bonusPaidMessage.setAppraisalId(message.getAppraisalId());
		bonusPaidMessage.setEmployeeId(message.getEmployeeId());
		bonusPaidMessage.setBonusAmount(-2500);
		bonusPaidMessage.setBonusPaidDateTime(LocalDateTime.of(LocalDate.of(2021, 06, 30), LocalTime.now()));
		bonusPaidMessage.setPaidToBankAccount("9289441602");

		LOG.debug("[Orchestration-Compensating Saga] Publishing to bonus payment response topic for appraisal {}",
				bonusPaidMessage.getAppraisalId());
		return bonusPaidMessage;
	}

}
