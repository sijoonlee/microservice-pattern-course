package com.course.microservice.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CqrsPerformanceHistory {

	@Column
	private LocalDateTime appraisalCreatedDate;

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID appraisalId;

	@Column
	private LocalDateTime approvedDateTime;

	@Column
	private double bonusAmount;

	@Column
	private LocalDateTime bonusPaidDate;

	@Column(length = 50)
	private String bonusPaidToBankAccount;

	@Column(nullable = false, length = 30)
	private String employeeId;

	@Column(nullable = false, length = 30)
	private String grade;

	@Column(nullable = false)
	private int score;

	@Column(nullable = false, length = 30)
	private String status;

	public LocalDateTime getAppraisalCreatedDate() {
		return appraisalCreatedDate;
	}

	public UUID getAppraisalId() {
		return appraisalId;
	}

	public LocalDateTime getApprovedDateTime() {
		return approvedDateTime;
	}

	public double getBonusAmount() {
		return bonusAmount;
	}

	public LocalDateTime getBonusPaidDate() {
		return bonusPaidDate;
	}

	public String getBonusPaidToBankAccount() {
		return bonusPaidToBankAccount;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getGrade() {
		return grade;
	}

	public int getScore() {
		return score;
	}

	public String getStatus() {
		return status;
	}

	public void setAppraisalCreatedDate(LocalDateTime appraisalCreatedDate) {
		this.appraisalCreatedDate = appraisalCreatedDate;
	}

	public void setAppraisalId(UUID appraisalId) {
		this.appraisalId = appraisalId;
	}

	public void setApprovedDateTime(LocalDateTime approvedDateTime) {
		this.approvedDateTime = approvedDateTime;
	}

	public void setBonusAmount(double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public void setBonusPaidDate(LocalDateTime bonusPaidDate) {
		this.bonusPaidDate = bonusPaidDate;
	}

	public void setBonusPaidToBankAccount(String bonusPaidToBankAccount) {
		this.bonusPaidToBankAccount = bonusPaidToBankAccount;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CqrsPerformanceHistory [appraisalCreatedDate=" + appraisalCreatedDate + ", appraisalId=" + appraisalId
				+ ", approvedDateTime=" + approvedDateTime + ", bonusAmount=" + bonusAmount + ", bonusPaidDate="
				+ bonusPaidDate + ", bonusPaidToBankAccount=" + bonusPaidToBankAccount + ", employeeId=" + employeeId
				+ ", grade=" + grade + ", score=" + score + ", status=" + status + "]";
	}

}
