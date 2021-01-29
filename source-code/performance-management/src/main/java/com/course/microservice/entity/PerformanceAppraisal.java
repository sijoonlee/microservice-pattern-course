package com.course.microservice.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PerformanceAppraisal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "BINARY(16)")
	private UUID appraisalId;

	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdDateTime;

	@Column(nullable = false, length = 30)
	private String employeeId;

	@Column(nullable = false, length = 30)
	private String grade;

	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime lastUpdatedDateTime;

	@Column(nullable = false)
	private int score;

	@Column(nullable = false, length = 30)
	private String status;

	public UUID getAppraisalId() {
		return appraisalId;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getGrade() {
		return grade;
	}

	public LocalDateTime getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public int getScore() {
		return score;
	}

	public String getStatus() {
		return status;
	}

	@JsonIgnore
	public boolean isFinalState() {
		return getStatus().equals(PerformanceAppraisalStatus.APPROVED.toString())
				|| getStatus().equals(PerformanceAppraisalStatus.APPROVAL_ERROR.toString());
	}

	public void setAppraisalId(UUID appraisalId) {
		this.appraisalId = appraisalId;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PerformanceAppraisal [appraisalId=" + appraisalId + ", createdDateTime=" + createdDateTime
				+ ", employeeId=" + employeeId + ", grade=" + grade + ", lastUpdatedDateTime=" + lastUpdatedDateTime
				+ ", score=" + score + ", status=" + status + "]";
	}

}
