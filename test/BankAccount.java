package com.meritamerica.assignment3;

import java.text.*;

public class BankAccount {
	protected static double balance;
	protected static double interestRate;
	protected static long accountNumber;
	protected static java.util.Date startDate;
	
	BankAccount(){} //Default constructor
	BankAccount(double balance, double interestRate) { //REQUIRED
		BankAccount.balance = balance;
		BankAccount.interestRate = interestRate;
	}
	BankAccount(double balance, double interestRate, java.util.Date accountOpenedOn){ //REQUIRED
		BankAccount.balance = balance;
		BankAccount.interestRate = interestRate;
		BankAccount.startDate = accountOpenedOn;
	}
	BankAccount(long accountNumber, double balance, 
				double interestRate, java.util.Date accountOpenedOn){ //REQUIRED
		BankAccount.accountNumber = accountNumber;
		BankAccount.balance = balance;
		BankAccount.interestRate = interestRate;
		BankAccount.startDate = accountOpenedOn;
	}
	public long getAccountNumber() {
		return BankAccount.accountNumber;
	}
	public double getBalance() {
		return BankAccount.balance;
	}
	public double getInterestRate() {
		return BankAccount.interestRate;
	}
	java.util.Date getOpenedOn(){
		return BankAccount.startDate;
	}
	static BankAccount readFromString(String accountData) throws ParseException{
		String[] parts = accountData.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
		
		if(accountData.length() > 0) {
			BankAccount.accountNumber = Long.parseLong(parts[0]);
			BankAccount.balance = Double.parseDouble(parts[1]);
			BankAccount.interestRate = Double.parseDouble(parts[2]);
		}
		return null;
	}
	boolean withdraw(double amount) {
		return false;
	}
	boolean deposit(double amount) {
		return false;
	}
	public double futureValue(int years) {
		double futureValue = getBalance() * Math.pow(1 + getInterestRate(), years);
		return futureValue;
	}
	String writeToString() {
		return "";
	}
}
