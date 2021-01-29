package com.course.microservice.broker.message;

import java.time.LocalDate;
import java.util.UUID;

public class CqrsPerformanceBonusMessage {

	private UUID appraisalId;

	private double bonusAmount;

	private LocalDate bonusPaidDate;

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
		CqrsPerformanceBonusMessage other = (CqrsPerformanceBonusMessage) obj;
		if (appraisalId == null) {
			if (other.appraisalId != null)
				return false;
		} else if (!appraisalId.equals(other.appraisalId))
			return false;
		return true;
	}

	public UUID getAppraisalId() {
		return appraisalId;
	}

	public double getBonusAmount() {
		return bonusAmount;
	}

	public LocalDate getBonusPaidDate() {
		return bonusPaidDate;
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
		return result;
	}

	public void setAppraisalId(UUID appraisalId) {
		this.appraisalId = appraisalId;
	}

	public void setBonusAmount(double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public void setBonusPaidDate(LocalDate bonusPaidDate) {
		this.bonusPaidDate = bonusPaidDate;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setPaidToBankAccount(String paidToBankAccount) {
		this.paidToBankAccount = paidToBankAccount;
	}

	@Override
	public String toString() {
		return "CqrsPerformanceBonusMessage [appraisalId=" + appraisalId + ", bonusAmount=" + bonusAmount
				+ ", bonusPaidDate=" + bonusPaidDate + ", employeeId=" + employeeId + ", paidToBankAccount="
				+ paidToBankAccount + "]";
	}

}
