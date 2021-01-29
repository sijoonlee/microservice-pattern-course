package com.course.microservice.command.action;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.entity.CqrsPerformanceHistory;
import com.course.microservice.entity.PerformanceAppraisal;
import com.course.microservice.entity.PerformanceAppraisalStatus;
import com.course.microservice.repository.CqrsPerformanceHistoryRepository;

@Component
public class CqrsPerformanceHistoryAction {

	@Autowired
	private CqrsPerformanceHistoryRepository repository;

	public CqrsPerformanceHistory convertFromPerformanceAppraisal(PerformanceAppraisal appraisal) {
		var performanceHistory = new CqrsPerformanceHistory();

		performanceHistory.setAppraisalId(appraisal.getAppraisalId());
		performanceHistory.setEmployeeId(appraisal.getEmployeeId());
		performanceHistory.setGrade(appraisal.getGrade());
		performanceHistory.setScore(appraisal.getScore());
		performanceHistory.setStatus(appraisal.getStatus());
		performanceHistory.setAppraisalCreatedDate(appraisal.getCreatedDateTime());

		return performanceHistory;
	}

	public CqrsPerformanceHistory createHistory(CqrsPerformanceHistory performanceHistory) {
		return repository.save(performanceHistory);
	}

	public CqrsPerformanceHistory getPerformanceHistoryById(UUID appraisalId) throws NoSuchElementException {
		return repository.findById(appraisalId).orElseThrow();
	}

	public void updatePerformanceHistory_ApprovalOnProgress(UUID appraisalId) {
		repository.updatePerformanceHistory_ApprovalOnProgress(
				PerformanceAppraisalStatus.APPROVAL_ON_PROGRESS.toString(), appraisalId);
	}

	public void updatePerformanceHistory_Approved(LocalDateTime lastUpdatedDateTime, UUID appraisalId) {
		repository.updatePerformanceHistory_Approved(PerformanceAppraisalStatus.APPROVED.toString(),
				lastUpdatedDateTime, appraisalId);
	}

	public void updatePerformanceHistory_BonusPaid(double bonusAmount, LocalDate bonusPaidDate,
			String paidToBankAccount, UUID appraisalId) {
		repository.updatePerformanceHistory_BonusPaid(bonusAmount, bonusPaidDate, paidToBankAccount, appraisalId);
	}

}
