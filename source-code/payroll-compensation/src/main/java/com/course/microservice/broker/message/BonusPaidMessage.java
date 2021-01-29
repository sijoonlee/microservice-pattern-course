package com.course.microservice.broker.message;

import java.time.LocalDateTime;

public class BonusPaidMessage {

	private String appraisalId;
	private double bonusAmount;
	private LocalDateTime bonusPaidDateTime;
	private String employeeId;
	private String paidToBankAccount;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusPaidMessage other = (BonusPaidMessage) obj;
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
		if (paidToBankAccount == null) {
			if (other.paidToBankAccount != null)
				return false;
		} else if (!paidToBankAccount.equals(other.paidToBankAccount))
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

	public String getPaidToBankAccount() {
		return paidToBankAccount;
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
		result = prime * result + ((paidToBankAccount == null) ? 0 : paidToBankAccount.hashCode());
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

	public void setPaidToBankAccount(String paidToBankAccount) {
		this.paidToBankAccount = paidToBankAccount;
	}

	@Override
	public String toString() {
		return "BonusPaidMessage [appraisalId=" + appraisalId + ", bonusAmount=" + bonusAmount + ", bonusPaidDateTime="
				+ bonusPaidDateTime + ", employeeId=" + employeeId + ", paidToBankAccount=" + paidToBankAccount + "]";
	}

}
