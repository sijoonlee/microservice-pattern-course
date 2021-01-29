package com.course.microservice.command.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.command.action.CqrsOutboxAction;
import com.course.microservice.command.action.CqrsPerformanceAppraisalAction;
import com.course.microservice.entity.CqrsOutboxEventType;
import com.course.microservice.entity.PerformanceAppraisal;
import com.course.microservice.entity.PerformanceAppraisalStatus;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class CqrsPerformanceAppraisalService {

	private static final Logger LOG = LoggerFactory.getLogger(CqrsPerformanceAppraisalService.class);

	private static final String OUTBOX_AGGREGATE_TYPE = "performance-appraisal";

	@Autowired
	private CqrsOutboxAction outboxAction;

	@Autowired
	private CqrsPerformanceAppraisalAction transactionAction;

	@Transactional
	public void approvePerformanceAppraisal(String appraisalId) throws NoSuchElementException, JsonProcessingException {
		var performanceAppraisal = transactionAction.getAppraisalById(appraisalId);
		if (transactionAction.isAppraisalInFinalState(performanceAppraisal)) {
			return;
		}

		LOG.debug("[CQRS-01] Approving performance appraisal {}", appraisalId);

		transactionAction.updateStatusToApproval(performanceAppraisal);
		// update status to be published as outbox payload
		performanceAppraisal.setStatus(PerformanceAppraisalStatus.APPROVAL_ON_PROGRESS.toString());

		transactionAction.publishApprovalToSaga(performanceAppraisal);

		// CQRS
		var outbox = outboxAction.insertOutbox(OUTBOX_AGGREGATE_TYPE, performanceAppraisal.getAppraisalId().toString(),
				CqrsOutboxEventType.APPROVAL_ON_PROGRESS, performanceAppraisal);
		outboxAction.deleteOutbox(outbox);
	}

	@Transactional
	public PerformanceAppraisal createPerformanceAppraisal(String employeeId, String grade, int score)
			throws JsonProcessingException {
		LOG.debug("[CQRS-01] Creating performance appraisal");

		var savedEntity = transactionAction.createPerformanceAppraisal(employeeId, grade, score);

		var outbox = outboxAction.insertOutbox(OUTBOX_AGGREGATE_TYPE, savedEntity.getAppraisalId().toString(),
				CqrsOutboxEventType.NEW, savedEntity);

		// delete it so outbox table wont grow. Debezium reads from table log, so it's
		// safe to clear the outbox as soon as its inserted
		outboxAction.deleteOutbox(outbox);

		return savedEntity;
	}

	@Transactional
	public void finalizePerformanceAppraisal(String appraisalId)
			throws NoSuchElementException, JsonProcessingException {
		LOG.debug("[CQRS-01] Finalizing performance appraisal {}", appraisalId);

		var performanceAppraisal = transactionAction.getAppraisalById(appraisalId);
		transactionAction.finalizePerformanceAppraisal(appraisalId);

		// CQRS
		var outbox = outboxAction.insertOutbox(OUTBOX_AGGREGATE_TYPE, performanceAppraisal.getAppraisalId().toString(),
				CqrsOutboxEventType.APPROVED, performanceAppraisal);
		outboxAction.deleteOutbox(outbox);
	}

}
