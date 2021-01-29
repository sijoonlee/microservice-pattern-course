package com.course.microservice.api.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.response.CompositionPerformanceHistoryResponse;
import com.course.microservice.query.service.PerformanceHistoryQueryService;

@RestController
@RequestMapping("/api/composition")
public class PerformanceHistoryApi {

	@Autowired
	private PerformanceHistoryQueryService queryService;

	@GetMapping(value = "/performance_history", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CompositionPerformanceHistoryResponse>> getAllPerformanceHistory() {
		return ResponseEntity.ok().body(queryService.getAllPerformanceHistory());
	}

}
