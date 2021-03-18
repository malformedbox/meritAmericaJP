package com.meritamerica.assignment4;

import java.text.SimpleDateFormat;

public abstract class Transaction {
	public static BankAccount getSourceAccount() {
		return null;
	}
	public void setSourceAccount(BankAccount sourceAccount) {
		
	}
	public static BankAccount getTargetAccount() {
		return null;
	}
	public void setTargetAccount(BankAccount targetAccount) {
		
	}
	public double getAmount() {
		return 0;
	}
	public void setAmount(double amount) {
		
	}
	public java.util.Date getTransactionDate(){
		return null;
	}
	public void setTransactionDate(java.util.Date date) {
		
	}
	public String writeToString() {
		return "";
	}
	public static Transaction readFromString(String transactionDataString) {
		String[] parts = transactionDataString.split(",\\s*");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		//parts[0] = tells you if it's a withdraw or deposit type transaction
		//parts[1] = account number or account transferring TO
		//parts[2] = balance being handled, if it's < 0 it's a withdrawal, if it's > 0 it's a deposit
		//parts[3] = date of this transaction
		if(Integer.parseInt(parts[0]) == -1) { //-1 = withdraw/deposit		
			if(Integer.parseInt(parts[2]) >= 0) {//parts[2] > 0 = deposit
				return new DepositTransaction(getSourceAccount(), Double.parseDouble(transactionDataString));
				//Make sure to add to process
			} else { //parts[2] < 0 withdraw
				return new WithdrawTransaction(getSourceAccount(), Double.parseDouble(transactionDataString));
				//Make sure to add to process
			}
		} else if(Integer.parseInt(parts[0]) == 1) { //+1 = transfer
		//TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount)
			if(Integer.parseInt(parts[2]) >= 0) {
				return new TransferTransaction(getSourceAccount(), getTargetAccount(), Double.parseDouble(parts[2]));
				//Make sure to add to process
			} else {
				throw new NumberFormatException();
			}
		} else {
			throw new NumberFormatException();
		}		
	}
	public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;
	public boolean isProcessedByFraudTeam() {
		return false;
	}
	public void setProcessedByFraudTeam(boolean isProcessed) {
		
	}
	public String getRejectionReason() {
		return "";
	}
	public void setRejectionReason(String reason) {
		
	}

}
