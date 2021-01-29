package com.course.microservice.api.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.command.service.PerformanceIndicatorService;
import com.course.microservice.query.result.PerformanceIndicatorResult;
import com.course.microservice.query.service.PerformanceIndicatorQueryService;

@RestController
@RequestMapping("/api/indicator")
public class PerformanceIndicatorApi {

	private static final Logger LOG = LoggerFactory.getLogger(PerformanceIndicatorApi.class);

	@Autowired
	private PerformanceIndicatorService commandService;

	@Autowired
	private PerformanceIndicatorQueryService queryService;

	@PutMapping(value = { "", "/" }, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createDummyData(
			@RequestParam(name = "data_size", required = true, defaultValue = "500") int dataSize) {
		if (dataSize < 1 || dataSize > 1000) {
			return ResponseEntity.badRequest().body("data_size must between 1 and 1000");
		}

		LOG.debug("Creating {} dummy data", dataSize);
		commandService.createDummyData(dataSize);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Created " + dataSize + " dummy data (any old data removed)");
	}

	@GetMapping(value = { "", "/" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PerformanceIndicatorResult> getDummyData(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		var total = queryService.getDataCount();
		var items = queryService.getDataWithPagination(page, size);
		var result = new PerformanceIndicatorResult(items, total);
		result.setTotalPage((long) Math.ceil(total / size));
		
		return ResponseEntity.ok().body(result);
	}
}
