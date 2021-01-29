package com.course.microservice.api.client;

import feign.RetryableException;
import feign.Retryer;

public class FeignRetryer implements Retryer {

	int attempt = 0;
	private long backoff;
	private double backoffMultiplier = 1d;
	private int maxAttempts;

	public FeignRetryer() {
		this(2000, 3, 1);
	}

	public FeignRetryer(long backoff, int maxAttempts, double backoffMultiplier) {
		this.backoff = backoff;
		this.maxAttempts = maxAttempts;
		this.backoffMultiplier = backoffMultiplier;
	}

	@Override
	public Retryer clone() {
		return new FeignRetryer(backoff, maxAttempts, backoffMultiplier);
	}

	@Override
	public void continueOrPropagate(RetryableException e) {
		if (++attempt >= maxAttempts) {
			throw e;
		}
		System.out.println("Retrying feign, attempt " + attempt);

		try {
			Thread.sleep(backoff);
			backoff *= backoffMultiplier;
		} catch (InterruptedException ignored) {
			Thread.currentThread().interrupt();
		}
	}

}
