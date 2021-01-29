package com.course.microservice.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.course.microservice.entity.PerformanceBonus;

@Repository
@RepositoryRestResource(collectionResourceRel = "bonus", path = "bonus")
public interface PerformanceBonusRepository extends PagingAndSortingRepository<PerformanceBonus, UUID> {

}
