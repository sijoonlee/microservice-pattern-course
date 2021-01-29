package com.course.microservice.api.response;

public class PlainMessage {

	private String message;

	public PlainMessage() {

	}

	public PlainMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
