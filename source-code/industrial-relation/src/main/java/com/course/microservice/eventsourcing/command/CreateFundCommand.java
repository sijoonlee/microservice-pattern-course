package com.course.microservice.eventsourcing.command;

public class CreateFundCommand extends BaseEventSourcingCommand<String> {

	private final double fundInitialBalance;

	public CreateFundCommand(String id, double fundInitialBalance) {
		super(id);
		this.fundInitialBalance = fundInitialBalance;
	}

	public double getFundInitialBalance() {
		return fundInitialBalance;
	}

}
