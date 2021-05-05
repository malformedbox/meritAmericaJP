package com.meritamerica.assignment6.model.exceptions;

public class ExceedsFraudSuspicionLimitException extends Exception{
	public ExceedsFraudSuspicionLimitException(String errorMessage) {
		super(errorMessage);
	}
}
