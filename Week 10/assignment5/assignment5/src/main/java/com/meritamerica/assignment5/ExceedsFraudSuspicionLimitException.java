package com.meritamerica.assignment5;

public class ExceedsFraudSuspicionLimitException extends Exception{
	public ExceedsFraudSuspicionLimitException(String errorMessage) {
		super(errorMessage);
	}
}
