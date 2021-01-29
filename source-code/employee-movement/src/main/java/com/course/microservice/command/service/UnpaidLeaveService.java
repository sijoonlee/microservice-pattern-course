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
public class UnpaidLeaveService {

	private static final Logger LOG = LoggerFactory.getLogger(UnpaidLeaveService.class);

	@Autowired
	private PayrollClient payrollClient;

	public boolean approveUnpaidLeave(String employeeId, LocalDate unpaidLeaveDate) {
		LOG.debug("[Sync] Simulating unpaid-leave approval for employee : {}, unpaid-leave date : {}", employeeId,
				unpaidLeaveDate);
		LOG.debug("[Sync] Updating data on industrial relation microservice, unpaid-leave status : PENDING");

		try {
			// calling payroll microservice
			LOG.debug("[Sync] Start calling API on payroll compensation microservice");
			var requestBody = new MasterPayrollRequest();
			requestBody.setEffectiveDate(unpaidLeaveDate);
			payrollClient.disableMasterPayroll(employeeId, requestBody);
			LOG.debug("[Sync] Finish calling API on payroll compensation microservice");

			// finalize the approval unpaid-leave status (payroll already disabled)
			LOG.debug("[Sync] Updating data on industrial relation microservice, unpaid-leave status : APPROVED");

			return true;
		} catch (FeignException e) {
			LOG.warn("[Sync] Feign client Exception : {}", e.getMessage());
			LOG.debug("[Sync] Updating data on industrial relation microservice, unpaid-leave status : ERROR");
			return false;
		}
	}

}
