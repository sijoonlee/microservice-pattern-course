package com.course.microservice.command.service;

import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.microservice.broker.message.BonusPaidMessage;
import com.course.microservice.broker.message.BonusRecordedMessage;
import com.course.microservice.broker.message.FinalizeAppraisalMessage;
import com.course.microservice.broker.message.PayBonusMessage;
import com.course.microservice.broker.message.RecordBonusErrorMessage;
import com.course.microservice.broker.message.RecordBonusMessage;
import com.course.microservice.broker.publisher.OrchestrationSagaPublisher;
import com.course.microservice.entity.PerformanceAppraisalStatus;
import com.course.microservice.repository.PerformanceAppraisalRepository;

@Service
public class OrchestrationCompensatingPerformanceAppraisalSaga {

	private static final Logger LOG = LoggerFactory.getLogger(OrchestrationCompensatingPerformanceAppraisalSaga.class);

	@Autowired
	private OrchestrationSagaPublisher publisher;

	@Autowired
	private PerformanceAppraisalRepository repository;

	public void approvePerformanceAppraisal(String appraisalId) {
		// if appraisal already final, don't do anything
		var appraisal = repository.findById(UUID.fromString(appraisalId)).orElseThrow();
		if (appraisal.isFinalState()) {
			return;
		}

		LOG.debug("[Orchestration-Compensating Saga] Approving performance appraisal");

		// 1. change status to 'approval on progress'
		repository.updatePerformanceAppraisalStatusById(PerformanceAppraisalStatus.APPROVAL_ON_PROGRESS.toString(),
				UUID.fromString(appraisalId));

		// 2. publish 'appraisal approved message' to kafka
		var payBonusMessage = new PayBonusMessage();
		payBonusMessage.setAppraisalId(appraisalId);
		payBonusMessage.setEmployeeId(appraisal.getEmployeeId());
		payBonusMessage.setGrade(appraisal.getGrade());
		payBonusMessage.setScore(appraisal.getScore());

		publisher.publishToCompensatingBonusPayment(payBonusMessage);
	}

	private void handleBonusRecorded(BonusRecordedMessage convertedMessage) throws InterruptedException {
		LOG.debug("[Orchestration-Compensating Saga] Listening bonus recorded message for appraisal {}",
				convertedMessage.getAppraisalId());

		// simulate finalization process
		Thread.sleep(3000);

		// send the message to next topic in saga
		var finalizeAppraisalMessage = new FinalizeAppraisalMessage();
		finalizeAppraisalMessage.setAppraisalId(convertedMessage.getAppraisalId());
		finalizeAppraisalMessage.setBonusRecordedDateTime(convertedMessage.getBonusRecordedDateTime());

		LOG.debug("[Orchestration-Compensating Saga] Publishing to finalize appraisal request topic for appraisal {}",
				finalizeAppraisalMessage.getAppraisalId());
		publisher.publishToFinalizeAppraisal(finalizeAppraisalMessage);
	}

	private void handleRecordBonusError(RecordBonusErrorMessage message) {
		LOG.debug("[Orchestration-Compensating Saga] Listening bonus recorded message for appraisal {}",
				message.getAppraisalId());

		// send the message to next topic in saga
		LOG.debug(
				"[Orchestration-Compensating Saga] Publishing error message to appropriate "
						+ "request topic(s) for appraisal {} with bonus amount {}",
				message.getAppraisalId(), message.getBonusAmount());
		publisher.publishRecordBonusError(message);
	}

	@KafkaListener(topics = "t.saga04.payrollcompensation.response")
	public void listenBonusPaymentResponse(BonusPaidMessage message) throws InterruptedException {
		LOG.debug("[Orchestration-Compensating Saga] Listening bonus paid message for appraisal {}",
				message.getAppraisalId());

		// alternative for error checking logic (e.g. negative amount)

		// simulate process
		Thread.sleep(3000);

		// send the message to next topic in saga
		var recordBonusMessage = new RecordBonusMessage();
		recordBonusMessage.setAppraisalId(message.getAppraisalId());
		recordBonusMessage.setEmployeeId(message.getEmployeeId());
		recordBonusMessage.setBonusPaidDateTime(message.getBonusPaidDateTime());
		recordBonusMessage.setBonusAmount(message.getBonusAmount());

		LOG.debug("[Orchestration-Compensating Saga] Publishing to record bonus request topic for appraisal {}",
				recordBonusMessage.getAppraisalId());
		publisher.publishToCompensatingRecordBonus(recordBonusMessage);
	}

	@KafkaListener(topics = "t.saga04.organizationdevelopment.response")
	public void listenBonusRecordResponse(ConsumerRecord<String, Object> message) throws InterruptedException {
		if (message.value() instanceof RecordBonusErrorMessage) {
			handleRecordBonusError((RecordBonusErrorMessage) message.value());
			return;
		}

		// at this point we can only has BonusRecordedMessage
		handleBonusRecorded((BonusRecordedMessage) message.value());
	}

}
