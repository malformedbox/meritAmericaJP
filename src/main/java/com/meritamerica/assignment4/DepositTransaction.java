package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction{
	
	DepositTransaction(BankAccount targetAccount, double amount){
		setTargetAccount(targetAccount);
		setAmount(amount);
	}
	@Override
	public void process()
			throws NegativeAmountException, 
			ExceedsAvailableBalanceException, 
			ExceedsFraudSuspicionLimitException {
	}
}
