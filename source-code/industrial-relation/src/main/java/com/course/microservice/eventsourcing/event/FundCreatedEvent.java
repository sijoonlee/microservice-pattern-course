package com.course.microservice.eventsourcing.event;

public class FundCreatedEvent extends BaseEvent<String> {

	private final double fundInitialBalance;

	public FundCreatedEvent(String id, double fundInitialBalance) {
		super(id);
		this.fundInitialBalance = fundInitialBalance;
	}

	public double getFundInitialBalance() {
		return fundInitialBalance;
	}
}
