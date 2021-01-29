package com.course.microservice.api.response;

import java.time.LocalDate;
import java.util.UUID;

public class PerformanceBonus {

	private UUID appraisalId;
	private double bonusAmount;
	private LocalDate bonusPaidDate;
	private String employeeId;

	private String paidToBankAccount;

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

}
