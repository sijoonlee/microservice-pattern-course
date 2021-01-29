package com.course.microservice.broker.message;

import java.time.LocalDateTime;

public class BonusRecordedMessage {

	private String appraisalId;
	private LocalDateTime bonusRecordedDateTime;
	private String employeeId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusRecordedMessage other = (BonusRecordedMessage) obj;
		if (appraisalId == null) {
			if (other.appraisalId != null)
				return false;
		} else if (!appraisalId.equals(other.appraisalId))
			return false;
		if (bonusRecordedDateTime == null) {
			if (other.bonusRecordedDateTime != null)
				return false;
		} else if (!bonusRecordedDateTime.equals(other.bonusRecordedDateTime))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		return true;
	}

	public String getAppraisalId() {
		return appraisalId;
	}

	public LocalDateTime getBonusRecordedDateTime() {
		return bonusRecordedDateTime;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appraisalId == null) ? 0 : appraisalId.hashCode());
		result = prime * result + ((bonusRecordedDateTime == null) ? 0 : bonusRecordedDateTime.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		return result;
	}

	public void setAppraisalId(String appraisalId) {
		this.appraisalId = appraisalId;
	}

	public void setBonusRecordedDateTime(LocalDateTime bonusRecordedDateTime) {
		this.bonusRecordedDateTime = bonusRecordedDateTime;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "BonusRecordedMessage [appraisalId=" + appraisalId + ", employeeId=" + employeeId
				+ ", bonusRecordedDateTime=" + bonusRecordedDateTime + "]";
	}

}
