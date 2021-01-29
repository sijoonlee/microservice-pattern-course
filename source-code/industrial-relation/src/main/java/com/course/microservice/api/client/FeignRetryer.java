package com.course.microservice.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import feign.RetryableException;
import feign.Retryer;

public class FeignRetryer implements Retryer {

	private static final Logger LOG = LoggerFactory.getLogger(FeignRetryer.class);
	private long additionalBackoffInMillis;
	int attempt = 0;

	private int maxAttempts;

	public FeignRetryer() {
		this(3000, 10);
	}

	private FeignRetryer(long additionalBackoffInMillis, int maxAttempts) {
		this.additionalBackoffInMillis = additionalBackoffInMillis;
		this.maxAttempts = maxAttempts;
	}

	@Override
	public Retryer clone() {
		return new FeignRetryer(additionalBackoffInMillis, maxAttempts);
	}

	@Override
	public void continueOrPropagate(RetryableException e) {
		if (attempt++ >= maxAttempts) {
			throw e;
		}

		LOG.debug("Retrying feign, attempt {} of {}", attempt, maxAttempts);

		try {
			Thread.sleep(additionalBackoffInMillis);
		} catch (InterruptedException ignored) {
			Thread.currentThread().interrupt();
		}
	}

}