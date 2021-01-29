package com.course.microservice.broker.message;

public class MasterPayrollResponseMessage {

	private String employeeId;

	private String message;

	public String getEmployeeId() {
		return employeeId;
	}

	public String getMessage() {
		return message;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
