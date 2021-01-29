package com.course.microservice.repository;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.course.microservice.entity.PerformanceAppraisal;

@Repository
@RepositoryRestResource(collectionResourceRel = "appraisal", path = "appraisal")
public interface PerformanceAppraisalRepository extends PagingAndSortingRepository<PerformanceAppraisal, UUID> {

	@Modifying
	@Transactional
	@Query("UPDATE PerformanceAppraisal SET status = :status WHERE appraisal_id = :appraisalId")
	void updatePerformanceAppraisalStatusById(@Param("status") String status, @Param("appraisalId") UUID appraisalId);

}
