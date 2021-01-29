package com.course.microservice.api.request;

import java.time.LocalDate;

public class MasterPayrollRequest {

	private LocalDate effectiveDate;

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

}
