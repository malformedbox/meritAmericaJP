package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction{
	private BankAccount sourceAccount;
	private BankAccount targetAccount;
	private double amount;

	TransferTransaction(BankAccount sourceAccount, 
			BankAccount targetAccount, double amount){
		setSourceAccount(sourceAccount);
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
