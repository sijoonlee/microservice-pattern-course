package com.course.microservice.api.server;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.entity.PerformanceBonus;
import com.course.microservice.query.service.PerformanceBonusQueryService;

@RestController
@RequestMapping("/api/composition")
public class CompositionPerformanceBonusApi {

	@Autowired
	private PerformanceBonusQueryService queryService;

	@GetMapping(value = "/bonus/{appraisal_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PerformanceBonus> getPerformanceBonusById(@PathVariable("appraisal_id") String appraisalId)
			throws InterruptedException {
		try {
			// simulate delay
			Thread.sleep(500);
			return ResponseEntity.ok().body(queryService.getPerformanceBonusById(appraisalId));
		} catch (NoSuchElementException e) {
			// appraisal id not found
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
