package com.course.microservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OutboxPolling {

	@Column(length = 4000, nullable = false)
	private String changedData;

	@CreatedDate
	@Column
	private LocalDateTime createdDate;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "outbox_polling_seq")
	private long id;

	@Column(length = 20, nullable = false)
	private String transactionType;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutboxPolling other = (OutboxPolling) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getChangedData() {
		return changedData;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public long getId() {
		return id;
	}

	public String getTransactionType() {
		return transactionType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	public void setChangedData(String changedData) {
		this.changedData = changedData;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "OutboxPolling [id=" + id + ", transactionType=" + transactionType + ", changedData=" + changedData
				+ "]";
	}

}
