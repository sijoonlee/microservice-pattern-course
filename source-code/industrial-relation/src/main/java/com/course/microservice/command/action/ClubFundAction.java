
package com.course.microservice.command.action;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClubFundAction {

	@Autowired
	private CommandGateway commandGateway;

	public String generateClubId() {
		return UUID.randomUUID().toString();
	}

	public CompletableFuture<String> sendCommand(Object command) {
		return commandGateway.send(command);
	}

}
