package com.course.microservice.broker.message;

import java.time.LocalDateTime;

public class RecordBonusMessage {
	private String appraisalId;
	private double bonusAmount;
	private LocalDateTime bonusPaidDateTime;
	private String employeeId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecordBonusMessage other = (RecordBonusMessage) obj;
		if (appraisalId == null) {
			if (other.appraisalId != null)
				return false;
		} else if (!appraisalId.equals(other.appraisalId))
			return false;
		if (Double.doubleToLongBits(bonusAmount) != Double.doubleToLongBits(other.bonusAmount))
			return false;
		if (bonusPaidDateTime == null) {
			if (other.bonusPaidDateTime != null)
				return false;
		} else if (!bonusPaidDateTime.equals(other.bonusPaidDateTime))
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

	public double getBonusAmount() {
		return bonusAmount;
	}

	public LocalDateTime getBonusPaidDateTime() {
		return bonusPaidDateTime;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appraisalId == null) ? 0 : appraisalId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(bonusAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((bonusPaidDateTime == null) ? 0 : bonusPaidDateTime.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		return result;
	}

	public void setAppraisalId(String appraisalId) {
		this.appraisalId = appraisalId;
	}

	public void setBonusAmount(double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public void setBonusPaidDateTime(LocalDateTime bonusPaidDateTime) {
		this.bonusPaidDateTime = bonusPaidDateTime;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "RecordBonusMessage [appraisalId=" + appraisalId + ", bonusPaidDateTime=" + bonusPaidDateTime
				+ ", bonusAmount=" + bonusAmount + ", employeeId=" + employeeId + "]";
	}

}
