package com.course.microservice.command.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.BonusPaidMessage;

@Component
public class CqrsBonusRecordAction {

	private static final Logger LOG = LoggerFactory.getLogger(CqrsBonusRecordAction.class);

	public void simulateRecordBonus(BonusPaidMessage message) {
		LOG.debug("[CQRS-01] Simulating bonus record for appraisal id : {}", message.getAppraisalId());
	}

}
