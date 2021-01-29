package com.course.microservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.course.microservice.entity.PerformanceIndicator;

@Repository
@RepositoryRestResource(collectionResourceRel = "indicator_spring", path = "indicator_spring")
public interface PerformanceIndicatorRepository extends PagingAndSortingRepository<PerformanceIndicator, Integer> {

}
