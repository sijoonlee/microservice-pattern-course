package com.course.microservice.api.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.entity.PerformanceAppraisal;
import com.course.microservice.query.service.PerformanceAppraisalQueryService;

@RestController
@RequestMapping("/api/composition")
public class CompositionPerformanceAppraisalApi {

	@Autowired
	private PerformanceAppraisalQueryService queryService;

	@GetMapping(value = "/appraisal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PerformanceAppraisal>> getAllPerformanceAppraisal() {
		return ResponseEntity.ok().body(queryService.getAllPerformanceAppraisal());
	}

}
