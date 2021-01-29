package com.course.microservice.command.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.api.client.PayrollClient;
import com.course.microservice.api.request.MasterPayrollRequest;

import feign.FeignException;

@Service
public class TerminationService {

	private static final Logger LOG = LoggerFactory.getLogger(TerminationService.class);

	@Autowired
	private PayrollClient payrollClient;

	public boolean approveTermination(String employeeId, LocalDate terminationDate) {
		LOG.debug("[Sync] Simulating termination approval for employee : {}, termination date : {}", employeeId,
				terminationDate);
		LOG.debug("[Sync] Updating data on industrial relation microservice, termination status : PENDING");

		try {
			// calling payroll microservice
			LOG.debug("[Sync] Start calling API on payroll compensation microservice");
			var requestBody = new MasterPayrollRequest();
			requestBody.setEffectiveDate(terminationDate);
			payrollClient.disableMasterPayroll(employeeId, requestBody);
			LOG.debug("[Sync] Finish calling API on payroll compensation microservice");

			// finalize the approval termination status (payroll already disabled)
			LOG.debug("[Sync] Updating data on industrial relation microservice, termination status : APPROVED");

			return true;
		} catch (FeignException e) {
			LOG.warn("[Sync] Feign client Exception : {}", e.getMessage());
			LOG.debug("[Sync] Updating data on industrial relation microservice, termination status : ERROR");
			return false;
		}
	}

}
