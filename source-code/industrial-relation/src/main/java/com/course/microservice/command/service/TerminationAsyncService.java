package com.course.microservice.command.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.broker.message.MasterPayrollMessage;
import com.course.microservice.broker.message.MasterPayrollResponseMessage;
import com.course.microservice.broker.publisher.PayrollPublisher;

@Service
public class TerminationAsyncService {

	private static final Logger LOG = LoggerFactory.getLogger(TerminationAsyncService.class);

	@Autowired
	private PayrollPublisher payrollPublisher;

	public boolean approveEmployeeTermination(String employeeId, LocalDate terminationDate) {
		LOG.debug("[Async-publish] Simulating termination approval for employee : {}, termination date : {}",
				employeeId, terminationDate);
		LOG.debug("[Async-publish] Updating data on industrial relation microservice, termination status : PENDING");

		try {
			// publishing to message broker
			LOG.debug("[Async-publish] Start publishing master payroll message to message broker");
			var messagePublish = new MasterPayrollMessage();
			messagePublish.setEmployeeId(employeeId);
			messagePublish.setEffectiveDate(terminationDate);
			payrollPublisher.publishMasterPayrollMessage(messagePublish);
			LOG.debug("[Async-publish] Finish publishing master payroll message to message broker");

			return true;
		} catch (Exception e) {
			LOG.warn("[Async-publish] Client exception : {}", e.getMessage());
			LOG.debug("[Async-publish] Updating data on industrial relation microservice, termination status : ERROR");
			return false;
		}
	}

	public void finalizeEmployeeTerminationApproval(MasterPayrollResponseMessage responseMessage) {
		LOG.debug(
				"[Async-listen] Updating data on industrial relation microservice, termination status : APPROVED, for employee {}",
				responseMessage.getEmployeeId());
	}

}
