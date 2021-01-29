package com.course.microservice.api.request;

import com.course.microservice.entity.Employee;

/**
 * Sample employee API request
 */
public class EmployeeRequest {

	private Employee employee;
	private String reccomendedBy;

	public Employee getEmployee() {
		return employee;
	}


	public String getReccomendedBy() {
		return reccomendedBy;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public void setReccomendedBy(String reccomendedBy) {
		this.reccomendedBy = reccomendedBy;
	}

}
