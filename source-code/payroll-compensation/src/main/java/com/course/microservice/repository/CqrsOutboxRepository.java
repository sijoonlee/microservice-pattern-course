package com.course.microservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.course.microservice.entity.CqrsOutbox;

public interface CqrsOutboxRepository extends CrudRepository<CqrsOutbox, Long> {

}
