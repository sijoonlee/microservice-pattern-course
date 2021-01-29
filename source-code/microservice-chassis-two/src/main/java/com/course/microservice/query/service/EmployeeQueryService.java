package com.course.microservice.query.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.entity.Employee;
import com.course.microservice.query.action.EmployeeQueryAction;

/**
 * Sample employee query service
 */
@Service
public class EmployeeQueryService {

	@Autowired
	private EmployeeQueryAction action;

	public List<Employee> getEmployeesByAge(int min, int max) {
		var list = action.findByAgeBetween(min, max);
		list.forEach(emp -> emp.setEmail(action.maskEmail(emp.getEmail())));
		
		return list;
	}

}
