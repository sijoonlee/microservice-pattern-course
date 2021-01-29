package com.course.microservice.api.request;

/**
 * API request body
 */
public class DummyRequest {

	private int numberRequest;

	private String textRequest;

	public int getNumberRequest() {
		return numberRequest;
	}

	public String getTextRequest() {
		return textRequest;
	}

	public void setNumberRequest(int numberRequest) {
		this.numberRequest = numberRequest;
	}

	public void setTextRequest(String textRequest) {
		this.textRequest = textRequest;
	}

}
