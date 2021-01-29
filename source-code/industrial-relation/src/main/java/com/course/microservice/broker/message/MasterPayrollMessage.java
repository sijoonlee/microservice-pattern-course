package com.course.microservice.broker.message;

import java.time.LocalDate;

public class MasterPayrollMessage {

	private LocalDate effectiveDate;

	private String employeeId;

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
