package com.course.microservice.api.server;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.axonframework.eventhandling.DomainEventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.request.AddFundRequest;
import com.course.microservice.api.request.CreateFundRequest;
import com.course.microservice.api.request.DeductFundRequest;
import com.course.microservice.api.response.PlainMessage;
import com.course.microservice.command.service.ClubFundService;
import com.course.microservice.query.service.ClubFundQueryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/api/event_sourcing/club_fund")
public class ClubFundApi {

	@Autowired
	private ClubFundService commandService;

	@Autowired
	private ClubFundQueryService queryService;

	@PutMapping("/{club_id}")
	public ResponseEntity<PlainMessage> addFundTo(@PathVariable(name = "club_id", required = true) String clubId,
			@RequestBody(required = true) AddFundRequest request)
			throws InterruptedException, ExecutionException, TimeoutException {
		commandService.addFundTo(clubId, request).get(5, TimeUnit.SECONDS);
		var result = new PlainMessage("Added : " + request.getAddAmount() + " to club : " + clubId);

		return ResponseEntity.ok().body(result);
	}

	@PostMapping
	public ResponseEntity<PlainMessage> createFund(@RequestBody(required = true) CreateFundRequest request)
			throws InterruptedException, ExecutionException, TimeoutException {
		var newClubId = commandService.createFund(request).get(5, TimeUnit.SECONDS);
		var result = new PlainMessage("New club id is : " + newClubId + ". Keep this ID.");

		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@DeleteMapping("/{club_id}")
	public ResponseEntity<PlainMessage> deductFundFrom(@PathVariable(name = "club_id", required = true) String clubId,
			@RequestBody(required = true) DeductFundRequest request)
			throws InterruptedException, ExecutionException, TimeoutException {
		commandService.deductFundFrom(clubId, request).get(5, TimeUnit.SECONDS);
		var result = new PlainMessage("Deducted : " + request.getDeductAmount() + " from club : " + clubId);

		return ResponseEntity.ok().body(result);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/{club_id}")
	public List<DomainEventMessage> listEventsForFund(@PathVariable(name = "club_id", required = true) String clubId) {
		return queryService.listAllEvents(clubId);
	}

	@GetMapping("/balance/{club_id}")
	public ResponseEntity<PlainMessage> findBalance(@PathVariable(name = "club_id", required = true) String clubId,
			@RequestParam(name = "date_time_to", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTimeTo)
			throws JsonMappingException, JsonProcessingException {
		var timeLimitExclusive = dateTimeTo.toInstant(ZoneOffset.UTC);
		var balanceAt = queryService.findBalanceBefore(clubId, timeLimitExclusive);
		var result = new PlainMessage("Balance at " + dateTimeTo + " is " + balanceAt);

		return ResponseEntity.ok().body(result);
	}

}
