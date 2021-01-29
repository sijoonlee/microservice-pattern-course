package com.course.microservice.command.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MasterPayrollService {

	private static final Logger LOG = LoggerFactory.getLogger(MasterPayrollService.class);

	public boolean disablePayroll(String employeeId, LocalDate effectiveDate) {
		LOG.debug("Disabling payroll for employee : {}, effective date : {}, will take few seconds simulation.",
				employeeId, effectiveDate);

		// simulating some delay
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return true;
	}

}
