package com.course.microservice.eventsourcing.command;

import javax.validation.constraints.Min;

public class DeductFundCommand extends BaseEventSourcingCommand<String> {

	@Min(1)
	private final double deductAmount;

	public DeductFundCommand(String id, double deductAmount) {
		super(id);
		this.deductAmount = deductAmount;
	}

	public double getDeductAmount() {
		return deductAmount;
	}

}
