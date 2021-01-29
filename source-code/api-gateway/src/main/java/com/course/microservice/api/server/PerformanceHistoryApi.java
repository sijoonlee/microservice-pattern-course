package com.course.microservice.api.server;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.course.microservice.api.response.CompositionPerformanceHistoryResponse;
import com.course.microservice.api.response.PerformanceAppraisal;
import com.course.microservice.api.response.PerformanceBonus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/composition")
public class PerformanceHistoryApi {

	private WebClient webClientPayrollCompensation;
	private WebClient webClientPerformanceManagement;

	public PerformanceHistoryApi() {
		this.webClientPerformanceManagement = WebClient.create("http://localhost:8884");
		this.webClientPayrollCompensation = WebClient.create("http://localhost:8883");
	}

	@GetMapping(value = "/performance_history", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<CompositionPerformanceHistoryResponse> getAllPerformanceHistory() {
		return webClientPerformanceManagement.get().uri("/api/composition/appraisal").retrieve()
				.bodyToFlux(PerformanceAppraisal.class).flatMap(this::getPerformanceHistory);
	}

	private Mono<CompositionPerformanceHistoryResponse> getPerformanceHistory(PerformanceAppraisal appraisal) {
		Mono<CompositionPerformanceHistoryResponse> response = webClientPayrollCompensation.get()
				.uri("/api/composition/bonus/" + appraisal.getAppraisalId()).retrieve()
				.bodyToMono(PerformanceBonus.class).onErrorReturn(new PerformanceBonus()).flatMap(bonus -> {
					var performanceHistory = new CompositionPerformanceHistoryResponse();
					performanceHistory.setAppraisalId(appraisal.getAppraisalId());
					performanceHistory.setBonusAmount(bonus.getBonusAmount());
					performanceHistory.setBonusPaidDate(bonus.getBonusPaidDate());
					performanceHistory.setEmployeeId(appraisal.getEmployeeId());
					performanceHistory.setGrade(appraisal.getGrade());
					performanceHistory.setScore(appraisal.getScore());
					performanceHistory.setStatus(appraisal.getStatus());

					return Mono.just(performanceHistory);
				});

		return response;
	}

}
