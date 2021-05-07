package com.meritamerica.assignment7.model.exceptions;

public class ExceedsFraudSuspicionLimitException extends Exception{
	public ExceedsFraudSuspicionLimitException(String errorMessage) {
		super(errorMessage);
	}
}
