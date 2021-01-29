package com.course.microservice.api.response;

import java.time.LocalDate;
import java.util.UUID;

public class CompositionPerformanceHistoryResponse {

	private UUID appraisalId;
	private double bonusAmount;
	private LocalDate bonusPaidDate;
	private String employeeId;
	private String grade;
	private int score;
	private String status;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositionPerformanceHistoryResponse other = (CompositionPerformanceHistoryResponse) obj;
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

	public String getGrade() {
		return grade;
	}

	public int getScore() {
		return score;
	}

	public String getStatus() {
		return status;
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
		return "CompositionPerformanceHistoryResponse [appraisalId=" + appraisalId + ", employeeId=" + employeeId
				+ ", grade=" + grade + ", score=" + score + ", status=" + status + ", bonusAmount=" + bonusAmount
				+ ", bonusPaidDate=" + bonusPaidDate + "]";
	}

}
