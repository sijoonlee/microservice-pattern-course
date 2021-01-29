package com.course.microservice.broker.listener.saga_choreography;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.BonusRecordedMessage;
import com.course.microservice.command.service.CoreographyPerformanceAppraisalService;

@Component
public class BonusRecordedListener {

	private static final Logger LOG = LoggerFactory.getLogger(BonusRecordedListener.class);

	@Autowired
	private CoreographyPerformanceAppraisalService performanceAppraisalService;

	@KafkaListener(topics = "t.saga01.organizationdevelopment")
	public void listenBonusRecorded(BonusRecordedMessage message) throws InterruptedException {
		LOG.debug("[Choreography-Saga] Listening bonus recorded message for appraisal {}", message.getAppraisalId());

		// simulate finalization process
		Thread.sleep(3000);

		LOG.debug("[Choreography-Saga] Finalizing appraisal for {}", message.getAppraisalId());
		performanceAppraisalService.finalizePerformanceAppraisal(message.getAppraisalId());
	}

}
