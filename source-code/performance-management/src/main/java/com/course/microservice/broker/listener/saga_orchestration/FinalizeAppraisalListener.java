package com.course.microservice.broker.listener.saga_orchestration;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.FinalizeAppraisalMessage;
import com.course.microservice.entity.PerformanceAppraisalStatus;
import com.course.microservice.repository.PerformanceAppraisalRepository;

@Component
public class FinalizeAppraisalListener {

	private static final Logger LOG = LoggerFactory.getLogger(FinalizeAppraisalListener.class);

	@Autowired
	private PerformanceAppraisalRepository repository;

	public void finalizePerformanceAppraisal(String appraisalId) {
		// ...
		// finalize appraisal
		// ...

		repository.updatePerformanceAppraisalStatusById(PerformanceAppraisalStatus.APPROVED.toString(),
				UUID.fromString(appraisalId));
	}

	@KafkaListener(topics = "t.saga03.performancemanagement.request")
	public void listenFinalizeAppraisal(FinalizeAppraisalMessage message) {
		LOG.debug("[Orchestration-Saga] Listening finalize appraisal message for appraisal {}",
				message.getAppraisalId());

		this.finalizePerformanceAppraisal(message.getAppraisalId());
	}

}
