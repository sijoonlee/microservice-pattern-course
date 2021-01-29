package com.course.microservice.broker.listener.saga_choreography;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.RecordBonusErrorMessage;

@Component
//use @KafkaListener here, combined with @KafkaHandler on method, for possibility of multi-type messages in one topic
//method will be invoked based on data type received by listener
@KafkaListener(topics = "t.saga02.organizationdevelopment")
public class BonusRecordedCompensatingListener {

	private static final Logger LOG = LoggerFactory.getLogger(BonusRecordedCompensatingListener.class);

	@KafkaHandler
	public void listenErrorFromBonusRecord(RecordBonusErrorMessage message) throws InterruptedException {
		LOG.debug("[Choreography-Compensating Saga] Listening bonus record error message for appraisal {}",
				message.getAppraisalId());

		// ...
		// Compensate previous transaction, e.g. debit bonus that already paid
		// (credited), with
		// same amount as wrong data
		// ...

		// simulate compensation
		Thread.sleep(3000);

		LOG.debug("[Choreography-Compensating Saga] Compensate previous transaction, e.g. debit bonus "
				+ "that already paid (credited), with same amount as wrong data : "
				+ message.getBonusAmount());
	}

}
