package com.course.microservice.eventsourcing.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.course.microservice.eventsourcing.command.AddFundCommand;
import com.course.microservice.eventsourcing.command.CreateFundCommand;
import com.course.microservice.eventsourcing.command.DeductFundCommand;
import com.course.microservice.eventsourcing.event.FundAddedEvent;
import com.course.microservice.eventsourcing.event.FundCreatedEvent;
import com.course.microservice.eventsourcing.event.FundDeductedEvent;

@Aggregate
public class ClubFundAggregate {

	private static final Logger LOG = LoggerFactory.getLogger(ClubFundAggregate.class);

	@AggregateIdentifier
	private String clubId;

	private double fundBalance;

	private String name;

	public ClubFundAggregate() {

	}

	@CommandHandler
	public ClubFundAggregate(CreateFundCommand command) {
		LOG.debug("on CommandHandler, create fund command, with value : {}", command.getFundInitialBalance());

		var fundCreatedEvent = new FundCreatedEvent(command.getId(), command.getFundInitialBalance());
		AggregateLifecycle.apply(fundCreatedEvent);
	}

	public String getClubId() {
		return clubId;
	}

	public double getFundBalance() {
		return fundBalance;
	}

	public String getName() {
		return name;
	}

	@CommandHandler
	protected void on(AddFundCommand command) {
		LOG.debug("on CommandHandler, add fund command, with value : {}", command.getAddAmount());

		var fundAddedEvent = new FundAddedEvent(command.getId(), command.getAddAmount());
		AggregateLifecycle.apply(fundAddedEvent);
	}

	@CommandHandler
	protected void on(DeductFundCommand command) {
		LOG.debug("on CommandHandler, deduct fund command, with value : {}", command.getDeductAmount());

		var fundDeductedEvent = new FundDeductedEvent(command.getId(), command.getDeductAmount());
		AggregateLifecycle.apply(fundDeductedEvent);
	}

	@EventSourcingHandler
	protected void on(FundAddedEvent event) {
		LOG.debug("on EventSourcingHandler, fund added event, with value : {}", event.getAddAmount());

		this.fundBalance += event.getAddAmount();
	}

	@EventSourcingHandler
	protected void on(FundCreatedEvent event) {
		LOG.debug("on EventSourcingHandler, fund created event, with value : {}", event.getFundInitialBalance());

		this.clubId = event.getId();
		this.fundBalance = event.getFundInitialBalance();
	}

	@EventSourcingHandler
	protected void on(FundDeductedEvent event) {
		LOG.debug("on EventSourcingHandler, fund deducted event, with value : {}", event.getDeductAmount());

		this.fundBalance -= event.getDeductAmount();
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public void setFundBalance(double fundBalance) {
		this.fundBalance = fundBalance;
	}

	public void setName(String name) {
		this.name = name;
	}

}
