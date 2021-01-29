package com.course.microservice.broker.message;

public class RecordBonusErrorMessage {

	private String appraisalId;
	private double bonusAmount;
	private String errorMessage;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecordBonusErrorMessage other = (RecordBonusErrorMessage) obj;
		if (appraisalId == null) {
			if (other.appraisalId != null)
				return false;
		} else if (!appraisalId.equals(other.appraisalId))
			return false;
		if (Double.doubleToLongBits(bonusAmount) != Double.doubleToLongBits(other.bonusAmount))
			return false;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		return true;
	}

	public String getAppraisalId() {
		return appraisalId;
	}

	public double getBonusAmount() {
		return bonusAmount;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appraisalId == null) ? 0 : appraisalId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(bonusAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		return result;
	}

	public void setAppraisalId(String appraisalId) {
		this.appraisalId = appraisalId;
	}

	public void setBonusAmount(double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "RecordBonusErrorMessage [appraisalId=" + appraisalId + ", bonusAmount=" + bonusAmount
				+ ", errorMessage=" + errorMessage + "]";
	}

}
