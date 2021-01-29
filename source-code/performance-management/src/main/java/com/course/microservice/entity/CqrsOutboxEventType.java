package com.course.microservice.entity;

public interface CqrsOutboxEventType {

	String APPROVAL_ON_PROGRESS = "APPROVAL_ON_PROGRESS";
	String APPROVED = "APPROVED";
	String BONUS_PAID = "BONUS_PAID";
	String NEW = "NEW";

}
