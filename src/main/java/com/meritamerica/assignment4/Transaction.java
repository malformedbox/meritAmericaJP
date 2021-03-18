package com.meritamerica.assignment4;

import java.text.SimpleDateFormat;

public abstract class Transaction {
	public BankAccount getSourceAccount() {
		return null;
	}
	public void setSourceAccount(BankAccount sourceAccount) {
		
	}
	public BankAccount getTargetAccount() {
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
		//parts[0] = tells you if it's a withdraw or deposit
		//parts[1] = account number or account transferring TO
		//parts[2] = balance being handled
		//parts[3] = date of this transaction
		if(Integer.parseInt(parts[0]) == -1) { //-1 = withdraw/deposit
			if(Integer.parseInt(parts[1]) < 0) {// -value = withdraw
				//parts[2]
				//Make sure to add to process
				return null;
			} else { //any value of 0+ is a deposit
				//parts[2]
				//Make sure to add to process
				return null;
			}
		} else if(Integer.parseInt(parts[0]) == 1) { //+1 = transfer
			//Make sure to add to process
			//parts[2]
			return null;
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
