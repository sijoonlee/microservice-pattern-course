package com.course.microservice.broker.listener.cqrs01;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.CqrsOutboxMessage;
import com.course.microservice.broker.message.CqrsPerformanceBonusMessage;
import com.course.microservice.command.service.CqrsPerformanceHistoryService;
import com.course.microservice.entity.CqrsOutboxEventType;
import com.course.microservice.entity.PerformanceAppraisal;
import com.course.microservice.util.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CqrsPerformanceHistoryListener {

	private static final Logger LOG = LoggerFactory.getLogger(CqrsPerformanceHistoryListener.class);

	private LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CqrsPerformanceHistoryService service;

	private void createNewPerformanceHistory(long timestamp, PerformanceAppraisal performanceAppraisal) {
		LOG.debug("[CQRS-01] Creating new performance history for appraisal id {}",
				performanceAppraisal.getAppraisalId());

		var outboxTimestamp = localDateTimeUtil.fromMillis(timestamp);
		performanceAppraisal.setCreatedDateTime(outboxTimestamp);
		performanceAppraisal.setLastUpdatedDateTime(outboxTimestamp);

		service.createNewPerformanceHistory(performanceAppraisal);
	}

	@KafkaListener(topics = { "t.cqrs01.performancemanagement.outbox.performance-appraisal",
			"t.cqrs01.payrollcompensation.outbox.performance-appraisal" }, containerFactory = "stringDeserializerContainerFactory")
	public void onPerformanceManagementDataChanged(@Header(name = KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
			@Payload String message) throws JsonMappingException, JsonProcessingException {
		var outboxMessage = objectMapper.readValue(message, CqrsOutboxMessage.class);

		// convert payload to correct class, based on outbox event type
		if (StringUtils.equalsAny(outboxMessage.getPayload().getEventType(), CqrsOutboxEventType.NEW,
				CqrsOutboxEventType.APPROVAL_ON_PROGRESS, CqrsOutboxEventType.APPROVED)) {
			var performanceAppraisal = objectMapper.readValue(outboxMessage.getPayload().getPayload(),
					PerformanceAppraisal.class);

			switch (outboxMessage.getPayload().getEventType()) {
			case CqrsOutboxEventType.NEW:
				createNewPerformanceHistory(timestamp, performanceAppraisal);
				break;
			case CqrsOutboxEventType.APPROVAL_ON_PROGRESS:
				updatePerformanceHistory_ApprovalOnProgress(timestamp, performanceAppraisal);
				break;
			case CqrsOutboxEventType.APPROVED:
				updatePerformanceHistory_Approved(timestamp, performanceAppraisal);
				break;
			}
		} else if (StringUtils.equalsAny(outboxMessage.getPayload().getEventType(), CqrsOutboxEventType.BONUS_PAID)) {
			var paidBonusMessage = objectMapper.readValue(outboxMessage.getPayload().getPayload(),
					CqrsPerformanceBonusMessage.class);
			updatePerformanceHistory_BonusPaid(paidBonusMessage);
		}
	}

	private void updatePerformanceHistory_ApprovalOnProgress(long timestamp,
			PerformanceAppraisal performanceAppraisal) {
		LOG.debug("[CQRS-01] Appraisal on progress, updating performance history for appraisal id {}",
				performanceAppraisal.getAppraisalId());

		var outboxTimestamp = localDateTimeUtil.fromMillis(timestamp);
		performanceAppraisal.setLastUpdatedDateTime(outboxTimestamp);

		service.updatePerformanceHistory_ApprovalOnProgress(performanceAppraisal);
	}

	private void updatePerformanceHistory_Approved(long timestamp, PerformanceAppraisal performanceAppraisal) {
		LOG.debug("[CQRS-01] Appraisal approved, updating performance history for appraisal id {}",
				performanceAppraisal.getAppraisalId());

		var outboxTimestamp = localDateTimeUtil.fromMillis(timestamp);
		performanceAppraisal.setLastUpdatedDateTime(outboxTimestamp);

		service.updatePerformanceHistory_Approved(performanceAppraisal);
	}

	private void updatePerformanceHistory_BonusPaid(CqrsPerformanceBonusMessage paidBonusMessage) {
		LOG.debug("[CQRS-01] Bonus paid, updating performance history for appraisal id {}",
				paidBonusMessage.getAppraisalId());

		service.updatePerformanceHistory_BonusPaid(paidBonusMessage);
	}

}
