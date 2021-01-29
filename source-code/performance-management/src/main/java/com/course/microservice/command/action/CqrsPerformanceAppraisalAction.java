package com.course.microservice.command.action;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.PerformanceAppraisalApprovedMessage;
import com.course.microservice.broker.publisher.CqrsPerformanceAppraisalPublisher;
import com.course.microservice.entity.PerformanceAppraisal;
import com.course.microservice.entity.PerformanceAppraisalStatus;
import com.course.microservice.repository.PerformanceAppraisalRepository;

@Component
public class CqrsPerformanceAppraisalAction {

	@Autowired
	private CqrsPerformanceAppraisalPublisher publisher;

	@Autowired
	private PerformanceAppraisalRepository transactionRepository;

	public PerformanceAppraisal createPerformanceAppraisal(String employeeId, String grade, int score) {
		var appraisal = new PerformanceAppraisal();
		appraisal.setEmployeeId(employeeId);
		appraisal.setGrade(grade);
		appraisal.setScore(score);
		appraisal.setStatus(PerformanceAppraisalStatus.NEW.toString());

		return transactionRepository.save(appraisal);
	}

	public void finalizePerformanceAppraisal(String appraisalId) {
		transactionRepository.updatePerformanceAppraisalStatusById(PerformanceAppraisalStatus.APPROVED.toString(),
				UUID.fromString(appraisalId));
	}

	public PerformanceAppraisal getAppraisalById(String appraisalId) throws NoSuchElementException {
		return transactionRepository.findById(UUID.fromString(appraisalId)).orElseThrow();
	}

	public boolean isAppraisalInFinalState(PerformanceAppraisal performanceAppraisal) {
		return performanceAppraisal.isFinalState();
	}

	public void publishApprovalToSaga(PerformanceAppraisal performanceAppraisal) {
		var appraisalApprovedMessage = new PerformanceAppraisalApprovedMessage();
		appraisalApprovedMessage.setAppraisalId(performanceAppraisal.getAppraisalId().toString());
		appraisalApprovedMessage.setEmployeeId(performanceAppraisal.getEmployeeId());
		appraisalApprovedMessage.setGrade(performanceAppraisal.getGrade());
		appraisalApprovedMessage.setScore(performanceAppraisal.getScore());

		publisher.publishForCqrsSaga(appraisalApprovedMessage);
	}

	public void updateStatusToApproval(PerformanceAppraisal performanceAppraisal) {
		transactionRepository.updatePerformanceAppraisalStatusById(
				PerformanceAppraisalStatus.APPROVAL_ON_PROGRESS.toString(), performanceAppraisal.getAppraisalId());
	}

}
