package com.course.microservice.command.service;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.broker.message.PerformanceAppraisalApprovedMessage;
import com.course.microservice.broker.publisher.PerformanceAppraisalApprovedPublisher;
import com.course.microservice.entity.PerformanceAppraisalStatus;
import com.course.microservice.repository.PerformanceAppraisalRepository;

@Service
public class CoreographyCompensatingPerformanceAppraisalService {

	private static final Logger LOG = LoggerFactory.getLogger(CoreographyCompensatingPerformanceAppraisalService.class);

	@Autowired
	private PerformanceAppraisalApprovedPublisher publisher;

	@Autowired
	private PerformanceAppraisalRepository repository;

	public void approvePerformanceAppraisal(String appraisalId) throws NoSuchElementException {
		// if appraisal already final, don't do anything
		var appraisal = repository.findById(UUID.fromString(appraisalId)).orElseThrow();
		if (appraisal.isFinalState()) {
			return;
		}

		LOG.debug("[Choreography-Compensating Saga] Approving performance appraisal");

		// 1. change status to 'approval on progress'
		repository.updatePerformanceAppraisalStatusById(PerformanceAppraisalStatus.APPROVAL_ON_PROGRESS.toString(),
				UUID.fromString(appraisalId));

		// 2. publish 'appraisal approved message' to kafka
		var appraisalApprovedMessage = new PerformanceAppraisalApprovedMessage();
		appraisalApprovedMessage.setAppraisalId(appraisalId);
		appraisalApprovedMessage.setEmployeeId(appraisal.getEmployeeId());
		appraisalApprovedMessage.setGrade(appraisal.getGrade());
		appraisalApprovedMessage.setScore(appraisal.getScore());

		publisher.publishForCoreographyCompensatingSaga(appraisalApprovedMessage);
	}

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

}
