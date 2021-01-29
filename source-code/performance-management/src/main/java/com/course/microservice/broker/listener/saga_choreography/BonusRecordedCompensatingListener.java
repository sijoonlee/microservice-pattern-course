package com.course.microservice.broker.listener.saga_choreography;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.BonusRecordedMessage;
import com.course.microservice.broker.message.RecordBonusErrorMessage;
import com.course.microservice.command.service.CoreographyPerformanceAppraisalService;

@Component
//use @KafkaListener here, combined with @KafkaHandler on method, for possibility of multi-type messages in one topic
//method will be invoked based on data type received by listener
@KafkaListener(topics = "t.saga02.organizationdevelopment")
public class BonusRecordedCompensatingListener {

	private static final Logger LOG = LoggerFactory.getLogger(BonusRecordedCompensatingListener.class);

	@Autowired
	private CoreographyPerformanceAppraisalService performanceAppraisalService;

	@KafkaHandler
	public void listenBonusRecorded(BonusRecordedMessage message) throws InterruptedException {
		LOG.debug("[Choreography-Saga] Listening bonus recorded message for appraisal {}", message.getAppraisalId());

		// simulate finalization process
		Thread.sleep(3000);

		LOG.debug("[Choreography-Saga] Finalizing appraisal for {}", message.getAppraisalId());

		// update appraisal status to approved
		performanceAppraisalService.finalizePerformanceAppraisal(message.getAppraisalId());
	}

	@KafkaHandler
	public void listenErrorFromBonusRecord(RecordBonusErrorMessage message) throws InterruptedException {
		LOG.debug("[Choreography-Compensating Saga] Listening bonus record error message for appraisal {}",
				message.getAppraisalId());

		// simulate compensation
		Thread.sleep(3000);

		LOG.debug("[Choreography-Compensating Saga] Compensate previous transaction, e.g. create log history, "
				+ "then update status to error");

		// update appraisal status to error
		performanceAppraisalService.errorPerformanceAppraisal(message.getAppraisalId());
	}

}
