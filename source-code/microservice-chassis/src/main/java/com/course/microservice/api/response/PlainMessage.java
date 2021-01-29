package com.course.microservice.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Plain message")
public class PlainMessage {

	@Schema(description = "The message to be returned")
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
