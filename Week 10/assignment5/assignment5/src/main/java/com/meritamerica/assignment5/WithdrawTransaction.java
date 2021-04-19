package com.meritamerica.assignment5;

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
