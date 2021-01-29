package com.course.microservice.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.entity.CqrsOutbox;
import com.course.microservice.repository.CqrsOutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CqrsOutboxAction {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CqrsOutboxRepository outboxRepository;

	public void deleteOutbox(CqrsOutbox outbox) {
		outboxRepository.delete(outbox);
	}

	public CqrsOutbox insertOutbox(CqrsOutbox cqrsOutbox) {
		return outboxRepository.save(cqrsOutbox);
	}

	public CqrsOutbox insertOutbox(String aggregateType, String aggregateId, String transactionType, Object payload)
			throws JsonProcessingException {
		var cqrsOutbox = new CqrsOutbox();

		cqrsOutbox.setAggregateType(aggregateType);
		cqrsOutbox.setAggregateId(aggregateId);
		cqrsOutbox.setTranscationType(transactionType);
		cqrsOutbox.setPayload(objectMapper.writeValueAsString(payload));

		return insertOutbox(cqrsOutbox);
	}
}
