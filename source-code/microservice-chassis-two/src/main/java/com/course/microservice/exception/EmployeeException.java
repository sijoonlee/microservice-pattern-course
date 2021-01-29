package com.course.microservice.exception;

public class EmployeeException extends RuntimeException {

	private static final long serialVersionUID = -7195250948921639737L;

	public EmployeeException() {
		super();
	}

	public EmployeeException(String message) {
		super(message);
	}

}
