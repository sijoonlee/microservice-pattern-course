package com.course.microservice.command.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.broker.message.BonusPaidMessage;
import com.course.microservice.broker.message.BonusRecordedMessage;
import com.course.microservice.command.action.CqrsBonusRecordAction;

@Service
public class CqrsBonusRecordService {

	@Autowired
	private CqrsBonusRecordAction transactionAction;

	public BonusRecordedMessage convertToBonusRecordedMessage(BonusPaidMessage message) {
		var bonusRecordedMessage = new BonusRecordedMessage();
		bonusRecordedMessage.setAppraisalId(message.getAppraisalId());
		bonusRecordedMessage.setEmployeeId(message.getEmployeeId());
		bonusRecordedMessage.setBonusRecordedDateTime(LocalDateTime.of(LocalDate.of(2021, 06, 30), LocalTime.now()));

		return bonusRecordedMessage;
	}

	public void simulateRecordBonus(BonusPaidMessage message) {
		transactionAction.simulateRecordBonus(message);
	}

}
