package com.course.microservice.eventsourcing.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public abstract class BaseEventSourcingCommand<T> {

	@TargetAggregateIdentifier
	private final T id;

	public BaseEventSourcingCommand(T id) {
		this.id = id;
	}

	public T getId() {
		return id;
	}

}
