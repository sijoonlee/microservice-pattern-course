package com.course.microservice.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.course.microservice.api.request.MasterPayrollRequest;
import com.course.microservice.api.response.MasterPayrollResponse;

@FeignClient(name = "payroll-compensation", qualifier = "payrollClient")
public interface PayrollClient {

	@DeleteMapping(value = "/api/payroll/master/{employee_id}")
	ResponseEntity<MasterPayrollResponse> disableMasterPayroll(@PathVariable(name = "employee_id") String employeeId,
			@RequestBody MasterPayrollRequest requestBody);

}
