package com.course.microservice.api.request;

import java.time.LocalDate;

import javax.persistence.Column;

public class EmployeeAssignmentPollingRequest {

	@Column(nullable = false)
	private LocalDate dateEnd;

	@Column(nullable = false)
	private LocalDate dateStart;

	@Column(nullable = false, length = 30)
	private String employeeId;

	@Column(nullable = false)
	private String position;

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getPosition() {
		return position;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
