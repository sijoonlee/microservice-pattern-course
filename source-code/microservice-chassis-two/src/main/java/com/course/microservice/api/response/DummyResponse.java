package com.course.microservice.api.response;

/**
 * API response body
 * 
 */
public class DummyResponse {

	private int numberResponse;

	private String textResponse;

	public int getNumberResponse() {
		return numberResponse;
	}

	public String getTextResponse() {
		return textResponse;
	}

	public void setNumberResponse(int numberResponse) {
		this.numberResponse = numberResponse;
	}

	public void setTextResponse(String textResponse) {
		this.textResponse = textResponse;
	}

}
