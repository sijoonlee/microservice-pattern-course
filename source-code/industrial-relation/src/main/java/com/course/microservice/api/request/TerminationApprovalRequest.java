package com.course.microservice.api.request;

import java.time.LocalDate;

public class TerminationApprovalRequest {

	private String reason;

	private LocalDate terminationDate;

	public String getReason() {
		return reason;
	}

	public LocalDate getTerminationDate() {
		return terminationDate;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setTerminationDate(LocalDate terminationDate) {
		this.terminationDate = terminationDate;
	}

}
