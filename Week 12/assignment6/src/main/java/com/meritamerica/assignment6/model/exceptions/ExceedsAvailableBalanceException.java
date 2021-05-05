package com.meritamerica.assignment6.model.exceptions;

public class ExceedsAvailableBalanceException extends Exception{	
	public ExceedsAvailableBalanceException(String errorMessage) {
		super(errorMessage);
	}
}
