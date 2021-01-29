package com.course.microservice.broker.listener.cqrs01;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.BonusRecordedMessage;
import com.course.microservice.command.service.CqrsPerformanceAppraisalService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class CqrsBonusRecordedListener {

	private static final Logger LOG = LoggerFactory.getLogger(CqrsBonusRecordedListener.class);

	@Autowired
	private CqrsPerformanceAppraisalService performanceAppraisalService;

	@KafkaListener(topics = "t.cqrs01.organizationdevelopment")
	public void listenBonusRecorded(BonusRecordedMessage message)
			throws InterruptedException, NoSuchElementException, JsonProcessingException {
		LOG.debug("[CQRS-01] Listening bonus recorded message for appraisal {}", message.getAppraisalId());

		// simulate finalization process
		Thread.sleep(20);

		LOG.debug("[CQRS-01] Finalizing appraisal for {}", message.getAppraisalId());
		performanceAppraisalService.finalizePerformanceAppraisal(message.getAppraisalId());
	}

}
