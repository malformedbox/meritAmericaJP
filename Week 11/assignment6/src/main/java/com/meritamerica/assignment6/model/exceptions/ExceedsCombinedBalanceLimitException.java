package com.meritamerica.assignment6.model.exceptions;

public class ExceedsCombinedBalanceLimitException extends Exception{
	public ExceedsCombinedBalanceLimitException(String errorMessage) {
		super(errorMessage);
	}
}
