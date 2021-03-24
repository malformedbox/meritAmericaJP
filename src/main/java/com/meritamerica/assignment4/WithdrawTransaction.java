package com.meritamerica.assignment4;

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
