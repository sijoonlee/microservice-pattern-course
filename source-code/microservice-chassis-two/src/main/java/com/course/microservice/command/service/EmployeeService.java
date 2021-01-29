package com.course.microservice.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.command.action.EmployeeAction;
import com.course.microservice.entity.Employee;

/**
 * Sample employee command service
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeAction action;

	public boolean createEmployee(Employee employee) {
		action.validateEmployee(employee);

		// valid & created
		if (action.createEmployee(employee)) {
			action.sendNotification(employee);
			return true;
		}

		// valid but not created
		return false;
	}


}
