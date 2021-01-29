package com.course.microservice.broker.message;

public class DummyBrokerMessageOne {

	private int numberOne;
	private String textOne;

	public int getNumberOne() {
		return numberOne;
	}

	public String getTextOne() {
		return textOne;
	}

	public void setNumberOne(int numberOne) {
		this.numberOne = numberOne;
	}

	public void setTextOne(String textOne) {
		this.textOne = textOne;
	}

	@Override
	public String toString() {
		return "BrokerMessageOne [numberOne=" + numberOne + ", textOne=" + textOne + "]";
	}

}
