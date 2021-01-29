package com.course.microservice.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.broker.message.CqrsPerformanceBonusMessage;
import com.course.microservice.command.action.CqrsPerformanceHistoryAction;
import com.course.microservice.entity.CqrsPerformanceHistory;
import com.course.microservice.entity.PerformanceAppraisal;

@Service
public class CqrsPerformanceHistoryService {

	@Autowired
	private CqrsPerformanceHistoryAction action;

	public CqrsPerformanceHistory createNewPerformanceHistory(PerformanceAppraisal appraisal) {
		var newPerformanceHistoryData = action.convertFromPerformanceAppraisal(appraisal);
		return action.createHistory(newPerformanceHistoryData);
	}

	public void updatePerformanceHistory_ApprovalOnProgress(PerformanceAppraisal performanceAppraisal) {
		action.updatePerformanceHistory_ApprovalOnProgress(performanceAppraisal.getAppraisalId());
	}

	public void updatePerformanceHistory_Approved(PerformanceAppraisal performanceAppraisal) {
		action.updatePerformanceHistory_Approved(performanceAppraisal.getLastUpdatedDateTime(),
				performanceAppraisal.getAppraisalId());
	}

	public void updatePerformanceHistory_BonusPaid(CqrsPerformanceBonusMessage paidBonusMessage) {
		action.updatePerformanceHistory_BonusPaid(paidBonusMessage.getBonusAmount(),
				paidBonusMessage.getBonusPaidDate(), paidBonusMessage.getPaidToBankAccount(),
				paidBonusMessage.getAppraisalId());
	}

}
