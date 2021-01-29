package com.course.microservice.broker.listener.saga_orchestration;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.FinalizeAppraisalMessage;
import com.course.microservice.broker.message.RecordBonusErrorMessage;
import com.course.microservice.entity.PerformanceAppraisalStatus;
import com.course.microservice.repository.PerformanceAppraisalRepository;

@Component
@KafkaListener(topics = "t.saga04.performancemanagement.request")
public class FinalizeAppraisalCompensatingListener {

	private static final Logger LOG = LoggerFactory.getLogger(FinalizeAppraisalCompensatingListener.class);

	@Autowired
	private PerformanceAppraisalRepository repository;

	public void errorPerformanceAppraisal(String appraisalId) {
		// ...
		// Compensate previous transaction, e.g. update status, etc
		// ...

		repository.updatePerformanceAppraisalStatusById(PerformanceAppraisalStatus.APPROVAL_ERROR.toString(),
				UUID.fromString(appraisalId));
	}

	public void finalizePerformanceAppraisal(String appraisalId) {
		// ...
		// finalize appraisal
		// ...

		repository.updatePerformanceAppraisalStatusById(PerformanceAppraisalStatus.APPROVED.toString(),
				UUID.fromString(appraisalId));
	}

	@KafkaHandler
	public void listenErrorFinalizeAppraisal(RecordBonusErrorMessage message) {
		LOG.debug("[Orchestration-Saga] Listening bonus record error message for appraisal {}",
				message.getAppraisalId());

		this.errorPerformanceAppraisal(message.getAppraisalId());
	}

	@KafkaHandler
	public void listenFinalizeAppraisal(FinalizeAppraisalMessage message) {
		LOG.debug("[Orchestration-Saga] Listening finalize appraisal message for appraisal {}",
				message.getAppraisalId());

		this.finalizePerformanceAppraisal(message.getAppraisalId());
	}

}
