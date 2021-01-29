package com.course.microservice.scheduler;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.OutboxPollingMessage;
import com.course.microservice.broker.publisher.OutboxPollingPublisher;
import com.course.microservice.entity.OutboxPolling;
import com.course.microservice.repository.OutboxPollingRepository;
import com.google.common.collect.Lists;

@Component
public class PollingMessageRelay {

	@Autowired
	private OutboxPollingPublisher publisher;

	@Autowired
	private OutboxPollingRepository repository;

	private OutboxPollingMessage convertToMessage(OutboxPolling outbox) {
		var result = new OutboxPollingMessage();
		
		result.setChangedData(outbox.getChangedData());
		result.setId(outbox.getId());
		result.setTransactionType(outbox.getTransactionType());
		
		return result;
	}

	@Scheduled(fixedDelay = 5000)
	@Transactional
	public void pollAndPublish() {
		var allOutboxData = Lists.newArrayList(repository.findAll());

		for (var outbox : allOutboxData) {
			var outboxPollingMessage = convertToMessage(outbox);

			publisher.publish(outboxPollingMessage);
			repository.delete(outbox);
		}
	}

}
