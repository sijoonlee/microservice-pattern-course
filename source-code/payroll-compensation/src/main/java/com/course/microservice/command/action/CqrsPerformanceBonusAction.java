package com.course.microservice.command.action;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.entity.PerformanceBonus;
import com.course.microservice.repository.PerformanceBonusRepository;

@Component
public class CqrsPerformanceBonusAction {

	@Autowired
	private PerformanceBonusRepository repository;

	public PerformanceBonus createPerformanceBonus(String appraisalId, String employeeId) {
		var bonus = new PerformanceBonus();
		bonus.setAppraisalId(UUID.fromString(appraisalId));
		bonus.setBonusAmount(ThreadLocalRandom.current().nextInt(1000, 5000));
		bonus.setBonusPaidDate(LocalDate.of(2021, 06, 30).plusDays(1));
		bonus.setPaidToBankAccount(RandomStringUtils.randomNumeric(10));
		bonus.setEmployeeId(employeeId);

		return repository.save(bonus);
	}

}
