package com.meritamerica.assignment5;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class Transaction {
	private static BankAccount source;
	private static BankAccount target;
	private static long transactionDate;
	private double amount;
	
	public static BankAccount getSourceAccount() {
		return source;
	}
	public static void setSourceAccount(BankAccount sourceAccount) {
		source = sourceAccount;
	}
	public static BankAccount getTargetAccount() {
		return target;
	}
	public static void setTargetAccount(BankAccount targetAccount) {
		target = targetAccount;
	}
	public double getAmount() {
		return this.amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getTransactionDate(){
		return transactionDate;
	}
	public static void setTransactionDate(long date) {
		transactionDate = date;
	}
	public String writeToString() {
		//-1,8,9000,01/01/2020
		DecimalFormat noDecimalPlaces = new DecimalFormat("#");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		return noDecimalPlaces.format(getAmount()) + "," 
				+ dateFormatter.format(getTransactionDate());
	}
	public static Transaction readFromString(String transactionDataString) throws ParseException {
     		String[] parts = transactionDataString.split(",\\s*");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		//-1,10,5000,01/01/2020
		//parts[0] = is less than 0 (a negative), then it is a withdraw or deposit transaction
		//parts[0] = is greater than 0 (a positive), then it is the source acc #
		//parts[1] = account number or account transferring TO
		//parts[2] = balance being handled, if it's < 0 it's a withdrawal, if it's > 0 it's a deposit
		//parts[3] = date of this transaction
		if(transactionDataString.length() > 0 && parts.length == 4) {
			if(Integer.parseInt(parts[0]) < 0) {//Deposit or Withdraw
				if(Double.parseDouble(parts[2]) > 0) {//Deposit	
					setTransactionDate(Long.parseLong(parts[3]));
					return new DepositTransaction(MeritBank.mapOfAccounts.get(Long.parseLong(parts[1])), Double.parseDouble(parts[2]));
				} else if(Double.parseDouble(parts[2]) < 0) {//Withdraw
					setTransactionDate(Long.parseLong(parts[3]));
					return new WithdrawTransaction(MeritBank.mapOfAccounts.get(Long.parseLong(parts[1])), Double.parseDouble(parts[2]));
				}
			}
		} else if(Long.parseLong(parts[0]) > 0) {//Transfer
			setTransactionDate(Long.parseLong(parts[3]));
			return new TransferTransaction(MeritBank.mapOfAccounts.get(Long.parseLong(parts[0])), MeritBank.mapOfAccounts.get(Long.parseLong(parts[1])), Double.parseDouble(parts[2]));
		} else {
			throw new NumberFormatException();
		}	
		return null;
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
