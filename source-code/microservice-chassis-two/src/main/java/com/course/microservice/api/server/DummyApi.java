package com.course.microservice.api.server;

import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.QueryParam;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.microservice.api.request.DummyRequest;
import com.course.microservice.api.response.DummyResponse;

/**
 * Dummy API
 */
@RestController
@RequestMapping("/api/server")
public class DummyApi {

	@DeleteMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DummyResponse> delete(@QueryParam("parameter") String parameter) {
		return ResponseEntity.ok(randomApiResponse());
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DummyResponse> get(@RequestParam String parameter) {
		return ResponseEntity.ok(randomApiResponse());
	}

	@PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DummyResponse> patch(@RequestBody DummyRequest request) {
		return ResponseEntity.ok(randomApiResponse());
	}

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DummyResponse> post(@RequestBody DummyRequest request) {
		return ResponseEntity.ok(randomApiResponse());
	}

	private DummyResponse randomApiResponse() {
		var result = new DummyResponse();
		result.setNumberResponse(ThreadLocalRandom.current().nextInt());
		result.setTextResponse(RandomStringUtils.randomAlphanumeric(8));

		return result;
	}

}
