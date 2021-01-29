package com.course.microservice.query.action;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.eventsourcing.event.FundAddedEvent;
import com.course.microservice.eventsourcing.event.FundCreatedEvent;
import com.course.microservice.eventsourcing.event.FundDeductedEvent;

@Component
@SuppressWarnings("rawtypes")
public class ClubFundQueryAction {

	@Autowired
	private EventStore eventStore;

	public FundAddedEvent castToFundAddedEvent(DomainEventMessage evt) {
		return (FundAddedEvent) evt.getPayload();
	}

	public FundCreatedEvent castToFundCreatedEvent(DomainEventMessage evt) {
		return (FundCreatedEvent) evt.getPayload();
	}

	public FundDeductedEvent castToFundDeductedEvent(DomainEventMessage evt) {
		return (FundDeductedEvent) evt.getPayload();
	}

	public boolean isFundAddedEvent(DomainEventMessage evt) {
		return evt.getPayloadType().getName().equals(FundAddedEvent.class.getName());
	}

	public boolean isFundCreatedEvent(DomainEventMessage evt) {
		return evt.getPayloadType().getName().equals(FundCreatedEvent.class.getName());
	}

	public boolean isFundDeductedEvent(DomainEventMessage evt) {
		return evt.getPayloadType().getName().equals(FundDeductedEvent.class.getName());
	}

	public List<DomainEventMessage> listAllEvents(String clubId) {
		return eventStore.readEvents(clubId).asStream().collect(Collectors.toList());
	}

	public List<DomainEventMessage> listEventsBefore(String clubId, Instant timeLimitExclusive) {
		return eventStore.readEvents(clubId).asStream().filter(m -> m.getTimestamp().isBefore(timeLimitExclusive))
				.collect(Collectors.toList());
	}

	public double retrieveEventAmount(DomainEventMessage evt) {
		if (this.isFundCreatedEvent(evt)) {
			return castToFundCreatedEvent(evt).getFundInitialBalance();
		} else if (this.isFundAddedEvent(evt)) {
			return castToFundAddedEvent(evt).getAddAmount();
		} else if (this.isFundDeductedEvent(evt)) {
			return -1 * castToFundDeductedEvent(evt).getDeductAmount();
		}

		return 0;
	}

}
