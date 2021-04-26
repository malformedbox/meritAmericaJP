package com.meritamerica.assignment6.model;

import com.meritamerica.assignment6.model.exceptions.ExceedsAvailableBalanceException;
import com.meritamerica.assignment6.model.exceptions.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment6.model.exceptions.NegativeAmountException;

public class WithdrawTransaction extends Transaction{

	WithdrawTransaction(BankAccount targetAccount, double amount){
		setSourceAccount(targetAccount);
		setAmount(amount);

	}
	@Override
	public void process()
			throws NegativeAmountException, 
			ExceedsAvailableBalanceException, 
			ExceedsFraudSuspicionLimitException {	
	}
}
