package com.course.microservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "cqrs_outbox")
public class CqrsOutbox {

	@Column(name = "aggregate_id")
	private String aggregateId;

	@Column(nullable = false, length = 80, name = "aggregate_type")
	private String aggregateType;

	// need to manually set this to millis (3 precision), as hibernate automatically
	// set precision to 6 (microseconds)
	// since this will be used later in listener, it's easier to convert from millis
	// instead micros
	@CreationTimestamp
	@Column(columnDefinition = "DATETIME(3) NOT NULL")
	private LocalDateTime createdTimestamp;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cqrs_outbox_seq")
	@SequenceGenerator(name = "cqrs_outbox_seq")
	private long id;

	/**
	 * JSON structure with the actual event contents
	 */
	@Column(nullable = false, length = 4000)
	private String payload;

	@Column(nullable = false, length = 80, name = "transaction_type")
	private String transcationType;

	public String getAggregateId() {
		return aggregateId;
	}

	public String getAggregateType() {
		return aggregateType;
	}

	public LocalDateTime getCreatedTimestamp() {
		return createdTimestamp;
	}

	public long getId() {
		return id;
	}

	public String getPayload() {
		return payload;
	}

	public String getTranscationType() {
		return transcationType;
	}

	public void setAggregateId(String aggregateId) {
		this.aggregateId = aggregateId;
	}

	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}

	public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public void setTranscationType(String transcationType) {
		this.transcationType = transcationType;
	}

}
