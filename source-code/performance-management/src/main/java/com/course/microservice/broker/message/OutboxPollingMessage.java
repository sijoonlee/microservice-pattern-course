package com.course.microservice.broker.message;

public class OutboxPollingMessage {


	private String changedData;

	private long id;

	private String transactionType;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutboxPollingMessage other = (OutboxPollingMessage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getChangedData() {
		return changedData;
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

	public void setId(long id) {
		this.id = id;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "OutboxPollingMessage [id=" + id + ", transactionType=" + transactionType + ", changedData="
				+ changedData + "]";
	}

}
