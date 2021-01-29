package com.course.microservice.broker.message;

public class CqrsOutboxMessage {
	public class CqrsPayload {
		private String eventType;
		// json string, with dynamic json structure depends on changed data
		private String payload;

		public String getEventType() {
			return eventType;
		}

		public String getPayload() {
			return payload;
		}

		public void setEventType(String eventType) {
			this.eventType = eventType;
		}

		public void setPayload(String payload) {
			this.payload = payload;
		}
	}

	private CqrsPayload payload;

	public CqrsPayload getPayload() {
		return payload;
	}

	public void setPayload(CqrsPayload payload) {
		this.payload = payload;
	}
}
