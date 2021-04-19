package com.meritamerica.assignment5;

public class ExceedsAvailableBalanceException extends Exception{	
	public ExceedsAvailableBalanceException(String errorMessage) {
		super(errorMessage);
	}
}
