package com.course.microservice.command.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.api.request.AddFundRequest;
import com.course.microservice.api.request.CreateFundRequest;
import com.course.microservice.api.request.DeductFundRequest;
import com.course.microservice.command.action.ClubFundAction;
import com.course.microservice.eventsourcing.command.AddFundCommand;
import com.course.microservice.eventsourcing.command.CreateFundCommand;
import com.course.microservice.eventsourcing.command.DeductFundCommand;

@Service
public class ClubFundService {

	@Autowired
	private ClubFundAction action;

	public CompletableFuture<String> addFundTo(String clubId, AddFundRequest request) {
		var addFundCommand = new AddFundCommand(clubId, request.getAddAmount());
		return action.sendCommand(addFundCommand);
	}

	public CompletableFuture<String> createFund(CreateFundRequest request) {
		var createFundCommand = new CreateFundCommand(action.generateClubId(), request.getFundInitialBalance());
		return action.sendCommand(createFundCommand);
	}

	public CompletableFuture<String> deductFundFrom(String clubId, DeductFundRequest request) {
		var deductFundCommand = new DeductFundCommand(clubId, request.getDeductAmount());
		return action.sendCommand(deductFundCommand);
	}

}
