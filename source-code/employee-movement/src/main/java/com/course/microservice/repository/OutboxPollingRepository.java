package com.course.microservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.course.microservice.entity.OutboxPolling;

@Repository
public interface OutboxPollingRepository extends PagingAndSortingRepository<OutboxPolling, Long> {

}
