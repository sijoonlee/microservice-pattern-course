package com.course.microservice.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.course.microservice.api.response.PerformanceBonus;

@FeignClient(name = "performanceBonusClient", url = "http://payroll-compensation")
public interface PerformanceBonusClient {

	@GetMapping(value = "/api/composition/bonus/{appraisal_id}")
	ResponseEntity<PerformanceBonus> getPerformanceBonus(@PathVariable(name = "appraisal_id") String appraisalId);

}
