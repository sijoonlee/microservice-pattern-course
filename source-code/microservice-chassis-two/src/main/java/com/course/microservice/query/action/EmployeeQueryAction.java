package com.course.microservice.query.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.entity.Employee;
import com.course.microservice.repository.EmployeeRepository;

/**
 * Sample employee query action
 */
@Component
public class EmployeeQueryAction {

	@Autowired
	private EmployeeRepository repository;

	public List<Employee> findByAgeBetween(int min, int max) {
		return repository.findByAgeBetween(min, max);
	}

	public String maskEmail(String email) {
		return StringUtils.overlay(email, "*", 0, 4);
	}

}
