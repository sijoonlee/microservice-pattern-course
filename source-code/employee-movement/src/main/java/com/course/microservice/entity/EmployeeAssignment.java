package com.course.microservice.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class EmployeeAssignment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_assignment_seq")
	@SequenceGenerator(name = "employee_assignment_seq")
	private long assignmentId;

	@Column(nullable = false)
	private LocalDate dateEnd;

	@Column(nullable = false)
	private LocalDate dateStart;

	@Column(nullable = false, length = 30)
	private String employeeId;

	@Column(nullable = false)
	private String position;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeAssignment other = (EmployeeAssignment) obj;
		if (assignmentId != other.assignmentId)
			return false;
		return true;
	}

	public long getAssignmentId() {
		return assignmentId;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getPosition() {
		return position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (assignmentId ^ (assignmentId >>> 32));
		return result;
	}

	public void setAssignmentId(long assignmentId) {
		this.assignmentId = assignmentId;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "EmployeeAssignment [assignmentId=" + assignmentId + ", dateEnd=" + dateEnd + ", dateStart=" + dateStart
				+ ", employeeId=" + employeeId + ", position=" + position + "]";
	}

}
