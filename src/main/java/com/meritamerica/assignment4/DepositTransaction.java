package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction{
	private double amount;
	
	DepositTransaction(BankAccount targetAccount, double amount){
		super();
		this.amount = amount;
	}
	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		/* 
		 * you will be forced to handle the exception when you call this method, 
		 * all the exceptions that are declared using throws, 
		 * must be handled where you are calling this method 
		 * else you will get compilation error.
		 */

	}
}
