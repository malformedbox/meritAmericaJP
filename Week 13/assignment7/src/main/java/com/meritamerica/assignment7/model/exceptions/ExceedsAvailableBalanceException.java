package com.meritamerica.assignment7.model.exceptions;

public class ExceedsAvailableBalanceException extends Exception{	
	public ExceedsAvailableBalanceException(String errorMessage) {
		super(errorMessage);
	}
}
