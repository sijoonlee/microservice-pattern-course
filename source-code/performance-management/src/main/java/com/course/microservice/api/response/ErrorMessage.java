package com.course.microservice.api.response;

public class ErrorMessage {

	private String message;

	private String reccomendation;

	public ErrorMessage() {

	}

	public ErrorMessage(String message, String reccomendation) {
		super();
		this.message = message;
		this.reccomendation = reccomendation;
	}

	public String getMessage() {
		return message;
	}

	public String getReccomendation() {
		return reccomendation;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setReccomendation(String reccomendation) {
		this.reccomendation = reccomendation;
	}
}
