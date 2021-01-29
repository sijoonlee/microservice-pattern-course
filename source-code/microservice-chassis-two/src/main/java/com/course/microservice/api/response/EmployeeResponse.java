package com.course.microservice.api.response;

import java.util.List;

import com.course.microservice.entity.Employee;

/**
 * Sample invoice API response
 *
 */
public class EmployeeResponse {

	private List<Employee> employees;

	public EmployeeResponse() {

	}

	public EmployeeResponse(List<Employee> employees) {
		super();
		this.employees = employees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
