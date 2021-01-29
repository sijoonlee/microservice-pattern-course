package com.course.microservice.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "slowPayrollClient", url = "http://payroll-compensation")
public interface SlowPayrollClient {

	@GetMapping(value = "/api/calculation/slow")
	ResponseEntity<String> slowCalculation(@RequestParam(value = "employee_id", required = true) String employeeId);

}
