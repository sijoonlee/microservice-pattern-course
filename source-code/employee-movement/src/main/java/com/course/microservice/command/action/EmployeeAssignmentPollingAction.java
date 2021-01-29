package com.course.microservice.command.action;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.entity.EmployeeAssignment;
import com.course.microservice.entity.OutboxPolling;
import com.course.microservice.repository.EmployeeAssignmentRepository;
import com.course.microservice.repository.OutboxPollingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmployeeAssignmentPollingAction {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private OutboxPollingRepository outboxRepository;

	@Autowired
	private EmployeeAssignmentRepository transactionRepository;

	@Transactional
	public void delete(long id) throws JsonProcessingException {
		transactionRepository.deleteById(id);
	}

	public void deleteById(long id) {
		transactionRepository.deleteById(id);
	}

	public Optional<EmployeeAssignment> findById(long id) {
		return transactionRepository.findById(id);
	}

	@Transactional
	public EmployeeAssignment insert(EmployeeAssignment entity) throws JsonProcessingException {
		return transactionRepository.save(entity);
	}

	public void insertIntoOutbox(Object entity, String transactionType) throws JsonProcessingException {
		var outboxPolling = new OutboxPolling();
		var changedDataAsJson = objectMapper.writeValueAsString(entity);

		outboxPolling.setChangedData(changedDataAsJson);
		outboxPolling.setTransactionType(transactionType);

		outboxRepository.save(outboxPolling);
	}

}
