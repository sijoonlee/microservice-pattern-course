package com.course.microservice.command.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.broker.message.BonusPaidMessage;
import com.course.microservice.broker.message.PerformanceAppraisalApprovedMessage;
import com.course.microservice.command.action.CqrsOutboxAction;
import com.course.microservice.command.action.CqrsPerformanceBonusAction;
import com.course.microservice.entity.CqrsOutboxEventType;
import com.course.microservice.entity.PerformanceBonus;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class CqrsPerformanceBonusService {

	private static final Logger LOG = LoggerFactory.getLogger(CqrsPerformanceBonusService.class);

	private static final String OUTBOX_AGGREGATE_TYPE = "performance-appraisal";

	@Autowired
	private CqrsOutboxAction outboxAction;

	@Autowired
	private CqrsPerformanceBonusAction transactionAction;

	public BonusPaidMessage convertToBonusPaidMessage(PerformanceAppraisalApprovedMessage message,
			PerformanceBonus paidBonus) {
		var bonusPaidMessage = new BonusPaidMessage();
		bonusPaidMessage.setAppraisalId(message.getAppraisalId());
		bonusPaidMessage.setEmployeeId(message.getEmployeeId());
		bonusPaidMessage.setBonusAmount(paidBonus.getBonusAmount());
		bonusPaidMessage.setBonusPaidDateTime(LocalDateTime.of(paidBonus.getBonusPaidDate(), LocalTime.now()));
		bonusPaidMessage.setPaidToBankAccount(paidBonus.getPaidToBankAccount());

		return bonusPaidMessage;
	}

	public PerformanceBonus payPerformanceBonus(PerformanceAppraisalApprovedMessage message)
			throws JsonProcessingException {
		LOG.debug("[CQRS-01] Creating performance bonus for {}", message.getAppraisalId());

		var savedEntity = transactionAction.createPerformanceBonus(message.getAppraisalId(), message.getEmployeeId());

		// CQRS
		var outbox = outboxAction.insertOutbox(OUTBOX_AGGREGATE_TYPE, savedEntity.getAppraisalId().toString(),
				CqrsOutboxEventType.BONUS_PAID, savedEntity);
		outboxAction.deleteOutbox(outbox);

		return savedEntity;
	}

}
