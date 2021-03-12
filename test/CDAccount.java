package com.meritamerica.assignment3;

import java.text.*;

public class CDAccount extends BankAccount {
	private CDOffering offering;
	private double balance;
	java.util.Date startDate;
	
	public CDAccount() {} //Default constructor
	
	public CDAccount(CDOffering offering, double balance) {
		super();
		this.offering = offering;
		this.balance = balance;
		this.startDate = new java.util.Date();
	}
	public double getBalance() {
		return this.balance;
	}
	public double getInterestRate() {
		return 0;
	}
	
	public int getTerm() {
		return 0;
	}
	public java.util.Date getStartDate(){
		return startDate;
	}
	
	public long getAccountNumber() {
		return super.accountNumber;
	}
	public double futureValue() {
		return getBalance() * Math.pow(1 + getInterestRate(), getTerm());
	}
	@Override
	boolean withdraw(double amount) {
		return false;
	}
	@Override
	boolean deposit(double amount) {
		return false;
	}
	static CDAccount readFromString(String accountData) throws ParseException{
		return null;
	}
}
