package com.course.microservice.api.response;

import java.util.UUID;

public class PerformanceAppraisal {
	private UUID appraisalId;
	private String employeeId;
	private String grade;
	private int score;
	private String status;

	public UUID getAppraisalId() {
		return appraisalId;
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

	public void setAppraisalId(UUID appraisalId) {
		this.appraisalId = appraisalId;
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
}
