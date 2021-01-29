package com.course.microservice.eventsourcing.command;

import javax.validation.constraints.Min;

public class AddFundCommand extends BaseEventSourcingCommand<String> {

	@Min(1)
	private final double addAmount;

	public AddFundCommand(String id, double addAmount) {
		super(id);
		this.addAmount = addAmount;
	}

	public double getAddAmount() {
		return addAmount;
	}

}
