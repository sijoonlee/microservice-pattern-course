package com.course.microservice.api.server;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RandomExceptionApi {

	private static final Logger LOG = LoggerFactory.getLogger(RandomStatusApi.class);

	@GetMapping(value = "/random/exception", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> randomHttpStatus() {
		var random = ThreadLocalRandom.current().nextInt(5);

		switch (random) {
		case 0:
			throw new IllegalArgumentException(
					"throwing some IllegalArgumentException");
		case 1:
			throw new IllegalArgumentException("throwing some IllegalArgumentException with random message : "
					+ RandomStringUtils.randomAlphanumeric(10));
		case 2:
			throw new ArithmeticException("throwing some ArithmeticException with random number between 0-9 : "
					+ ThreadLocalRandom.current().nextInt(10));
		default:
			LOG.info("Random exception : not thrown");
			return ResponseEntity.ok().body("OK");
		}
	}

}
