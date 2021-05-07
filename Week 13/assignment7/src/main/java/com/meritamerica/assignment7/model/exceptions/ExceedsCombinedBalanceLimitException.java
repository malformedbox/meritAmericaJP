package com.meritamerica.assignment7.model.exceptions;

public class ExceedsCombinedBalanceLimitException extends Exception{
	public ExceedsCombinedBalanceLimitException(String errorMessage) {
		super(errorMessage);
	}
}
