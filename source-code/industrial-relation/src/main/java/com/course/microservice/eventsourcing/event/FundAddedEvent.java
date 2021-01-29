package com.course.microservice.eventsourcing.event;

public class FundAddedEvent extends BaseEvent<String> {

	private final double addAmount;

	public FundAddedEvent(String id, double addAmount) {
		super(id);
		this.addAmount = addAmount;
	}

	public double getAddAmount() {
		return addAmount;
	}

}
