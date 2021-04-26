package com.meritamerica.assignment6.model;

import com.meritamerica.assignment6.model.exceptions.ExceedsAvailableBalanceException;
import com.meritamerica.assignment6.model.exceptions.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment6.model.exceptions.NegativeAmountException;

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