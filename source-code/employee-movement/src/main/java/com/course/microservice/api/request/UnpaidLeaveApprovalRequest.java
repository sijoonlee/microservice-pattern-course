package com.course.microservice.api.request;

import java.time.LocalDate;

public class UnpaidLeaveApprovalRequest {

	private String reason;

	private LocalDate unpaidLeaveDate;

	public String getReason() {
		return reason;
	}

	public LocalDate getUnpaidLeaveDate() {
		return unpaidLeaveDate;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setUnpaidLeaveDate(LocalDate unpaidLeaveDate) {
		this.unpaidLeaveDate = unpaidLeaveDate;
	}

}
