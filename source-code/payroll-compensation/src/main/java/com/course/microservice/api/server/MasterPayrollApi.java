package com.course.microservice.api.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.command.request.MasterPayrollRequest;
import com.course.microservice.command.response.MasterPayrollResponse;
import com.course.microservice.command.service.MasterPayrollService;

@RestController
@RequestMapping("/api/payroll/master")
public class MasterPayrollApi {

	private static final Logger LOG = LoggerFactory.getLogger(MasterPayrollApi.class);

	@Autowired
	private MasterPayrollService masterPayrollService;

	@DeleteMapping(value = "/{employee_id}")
	public ResponseEntity<MasterPayrollResponse> disableMasterPayroll(
			@PathVariable(name = "employee_id", required = true) String employeeId,
			@RequestBody(required = true) MasterPayrollRequest requestBody) {
		LOG.debug("[Sync] Start accessing payroll API, disabling master payroll");

		masterPayrollService.disablePayroll(employeeId, requestBody.getEffectiveDate());

		// dummy response
		var responseBody = new MasterPayrollResponse();
		responseBody.setEmployeeId(employeeId);
		responseBody.setMessage("Done simulating disable master payroll for " + employeeId);

		LOG.debug("[Sync] Finish accessing payroll API, disabling master payroll");

		return ResponseEntity.ok().body(responseBody);
	}

}
