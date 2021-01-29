package com.course.microservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.course.microservice.entity.EmployeeAssignment;

@Repository
@RepositoryRestResource(collectionResourceRel = "assignment", path = "assignment")
public interface EmployeeAssignmentRepository extends PagingAndSortingRepository<EmployeeAssignment, Long> {

}
