package com.course.microservice.api.server;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RandomStatusApi {

	private static final Logger LOG = LoggerFactory.getLogger(RandomStatusApi.class);

	@GetMapping(value = "/random/status", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> randomHttpStatus() {
		var random = ThreadLocalRandom.current().nextInt(6);

		switch (random) {
		case 0:
			LOG.warn("HTTP Status : bad request");
			return ResponseEntity.badRequest().body("Bad request");
		case 1:
			LOG.warn("HTTP Status : method not allowed");
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method not allowed");
		case 2:
			LOG.error("HTTP Status : bandwidth limit exceeded");
			return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body("Bandwidth limit exceeded");
		default:
			LOG.info("HTTP Status : ok");
			return ResponseEntity.ok().body("OK");
		}
	}

}
