package com.course.microservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PerformanceBonus {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID appraisalId;

	@Column
	private double bonusAmount;

	@Column
	private LocalDate bonusPaidDate;

	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdDateTime;

	@Column(nullable = false, length = 30)
	private String employeeId;

	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime lastUpdatedDateTime;

	private String paidToBankAccount;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerformanceBonus other = (PerformanceBonus) obj;
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

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public LocalDateTime getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
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

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

	public void setPaidToBankAccount(String paidToBankAccount) {
		this.paidToBankAccount = paidToBankAccount;
	}

	@Override
	public String toString() {
		return "PerformanceBonus [appraisalId=" + appraisalId + ", bonusAmount=" + bonusAmount + ", bonusPaidDate="
				+ bonusPaidDate + ", createdDateTime=" + createdDateTime + ", employeeId=" + employeeId
				+ ", lastUpdatedDateTime=" + lastUpdatedDateTime + ", paidToBankAccount=" + paidToBankAccount + "]";
	}

}
