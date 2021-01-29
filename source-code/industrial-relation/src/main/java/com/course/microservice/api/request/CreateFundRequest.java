package com.course.microservice.api.request;

public class CreateFundRequest {

	private double fundInitialBalance;

	public double getFundInitialBalance() {
		return fundInitialBalance;
	}

	public void setFundInitialBalance(double fundInitialBalance) {
		this.fundInitialBalance = fundInitialBalance;
	}

}
