package com.course.microservice.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.course.microservice.entity.CqrsPerformanceHistory;

@Repository
@RepositoryRestResource(collectionResourceRel = "performance_history", path = "performance_history")
public interface CqrsPerformanceHistoryRepository extends PagingAndSortingRepository<CqrsPerformanceHistory, UUID> {

	@Modifying
	@Transactional
	@Query("UPDATE CqrsPerformanceHistory SET status = :status WHERE appraisal_id = :appraisalId")
	void updatePerformanceHistory_ApprovalOnProgress(@Param("status") String status,
			@Param("appraisalId") UUID appraisalId);

	@Modifying
	@Transactional
	@Query("UPDATE CqrsPerformanceHistory SET status = :status, approved_date_time = :approvedDateTime "
			+ "WHERE appraisal_id = :appraisalId")
	void updatePerformanceHistory_Approved(@Param("status") String status,
			@Param("approvedDateTime") LocalDateTime approvedDateTime, @Param("appraisalId") UUID appraisalId);

	@Modifying
	@Transactional
	@Query("UPDATE CqrsPerformanceHistory SET status = 'BONUS_PAID', "
			+ "bonus_amount = :bonusAmount, bonus_paid_date = :bonusPaidDate, "
			+ "bonus_paid_to_bank_account = :paidToBankAccount " + "WHERE appraisal_id = :appraisalId")
	void updatePerformanceHistory_BonusPaid(@Param("bonusAmount") double bonusAmount,
			@Param("bonusPaidDate") LocalDate bonusPaidDate, @Param("paidToBankAccount") String paidToBankAccount,
			@Param("appraisalId") UUID appraisalId);

}
