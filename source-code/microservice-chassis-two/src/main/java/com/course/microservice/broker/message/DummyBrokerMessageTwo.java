package com.course.microservice.broker.message;

public class DummyBrokerMessageTwo {

	private int numberTwo;
	private String textTwo;

	public int getNumberTwo() {
		return numberTwo;
	}

	public String getTextTwo() {
		return textTwo;
	}

	public void setNumberTwo(int numberTwo) {
		this.numberTwo = numberTwo;
	}

	public void setTextTwo(String textTwo) {
		this.textTwo = textTwo;
	}

	@Override
	public String toString() {
		return "BrokerMessageTwo [numberTwo=" + numberTwo + ", textTwo=" + textTwo + "]";
	}

}
