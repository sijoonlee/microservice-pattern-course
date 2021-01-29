package com.course.microservice.query.service;

import java.time.Instant;
import java.util.List;

import org.axonframework.eventhandling.DomainEventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.query.action.ClubFundQueryAction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class ClubFundQueryService {

	@Autowired
	private ClubFundQueryAction action;

	@SuppressWarnings("rawtypes")
	public List<DomainEventMessage> listAllEvents(String clubId) {
		return action.listAllEvents(clubId);
	}

	public double findBalanceBefore(String clubId, Instant timeLimitExclusive)
			throws JsonMappingException, JsonProcessingException {
		return action.listEventsBefore(clubId, timeLimitExclusive).stream().mapToDouble(action::retrieveEventAmount)
				.sum();
	}

}
