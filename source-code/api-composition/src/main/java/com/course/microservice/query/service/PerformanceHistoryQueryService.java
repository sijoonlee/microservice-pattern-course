package com.course.microservice.query.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.microservice.api.response.CompositionPerformanceHistoryResponse;
import com.course.microservice.api.response.PerformanceAppraisal;
import com.course.microservice.api.response.PerformanceBonus;
import com.course.microservice.query.action.PerformanceHistoryQueryAction;

import feign.FeignException;

@Service
public class PerformanceHistoryQueryService {

	@Autowired
	private PerformanceHistoryQueryAction action;

	private CompositionPerformanceHistoryResponse aggregatePerformanceHistory(PerformanceAppraisal appraisal,
			PerformanceBonus bonus) {
		var history = new CompositionPerformanceHistoryResponse();

		history.setAppraisalId(appraisal.getAppraisalId());
		history.setEmployeeId(appraisal.getEmployeeId());
		history.setGrade(appraisal.getGrade());
		history.setScore(appraisal.getScore());
		history.setStatus(appraisal.getStatus());

		if (bonus != null) {
			history.setBonusAmount(bonus.getBonusAmount());
			history.setBonusPaidDate(bonus.getBonusPaidDate());
		}
		return history;
	}

	public List<CompositionPerformanceHistoryResponse> getAllPerformanceHistory() {
		// result variable
		var result = new ArrayList<CompositionPerformanceHistoryResponse>();

		// all appraisal data
		var allAppraisals = action.getAllPerformanceAppraisal();

		// get data for each appraisals
		for (var appraisal : allAppraisals) {
			PerformanceBonus bonus = null;

			try {
				bonus = action.getBonusForAppraisal(appraisal.getAppraisalId().toString());
			} catch (FeignException.NotFound bonusNotFound) {
				bonus = null;
			}

			// in memory join
			var performanceHistory = aggregatePerformanceHistory(appraisal, bonus);

			// put to result
			result.add(performanceHistory);
		}

		return result;
	}

}
