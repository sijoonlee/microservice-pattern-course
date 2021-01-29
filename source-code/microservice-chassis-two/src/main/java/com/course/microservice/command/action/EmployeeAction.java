package com.course.microservice.command.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.entity.Employee;
import com.course.microservice.exception.EmployeeException;
import com.course.microservice.repository.EmployeeRepository;

/**
 * Sample employee action
 */
@Component
public class EmployeeAction {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeAction.class);

	@Autowired
	private EmployeeRepository repository;

	public boolean createEmployee(Employee employee) {
		return repository.save(employee).getId() > 0 ? true : false;
	}

	public void sendNotification(Employee employee) {
		LOG.info("Sending new employee notification : {}", employee);
	}

	public void validateEmployee(Employee employee) throws EmployeeException {
		if (!StringUtils.endsWithIgnoreCase(employee.getEmail(), "@company.com")) {
			throw new EmployeeException("Invalid email : " + employee.getEmail());
		}
	}

}
