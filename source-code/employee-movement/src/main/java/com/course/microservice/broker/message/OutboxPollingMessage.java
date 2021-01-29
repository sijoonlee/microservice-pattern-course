package com.course.microservice.broker.message;

public class OutboxPollingMessage {

	private String changedData;

	private long id;

	private String transactionType;

	public String getChangedData() {
		return changedData;
	}

	public long getId() {
		return id;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setChangedData(String changedData) {
		this.changedData = changedData;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}
