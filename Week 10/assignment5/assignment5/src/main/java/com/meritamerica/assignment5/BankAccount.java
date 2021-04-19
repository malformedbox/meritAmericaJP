package com.meritamerica.assignment5;

import java.text.*;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BankAccount {
	protected long accountNumber;
	protected double balance;
	
	protected double interestRate;	
	protected long openedOn;
	private static List<Transaction> listOfTransactions = new ArrayList<Transaction>();
	
	BankAccount(){} //Default constructor
	BankAccount(double balance, double interestRate) { //REQUIRED
		this.accountNumber = MeritBank.getNextAccountNumber();
		this.balance = balance;
		this.interestRate = interestRate;
		this.openedOn = System.currentTimeMillis();
	}
	BankAccount(double balance, double interestRate, long accountOpenedOn){ //REQUIRED
		this.accountNumber = MeritBank.getNextAccountNumber();
		this.balance = balance;
		this.interestRate = interestRate;
		this.openedOn = accountOpenedOn;
	}
	BankAccount(long accountNumber, double balance, 
				double interestRate, long accountOpenedOn){ //REQUIRED
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.openedOn = accountOpenedOn;
	}
	public long getOpenedOn(){
		return this.openedOn;
	}
	public double getInterestRate() {
		return this.interestRate;
	}
	public double getBalance() {
		return this.balance;
	}
	public long getAccountNumber() {
		return this.accountNumber;
	}

	static BankAccount readFromString(String accountData) throws ParseException{
		String[] parts = accountData.split(",\\s*");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		
		if(accountData.length() > 0 && parts.length == 4) {
			//return new BankAccount(Long.parseLong(parts[0]), 
					//Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), dateFormatter.parse(parts[3]));
			return null;
		}else {
			throw new NumberFormatException();
		}
	}
	boolean withdraw(double amount) {
		if(amount < 0 || amount > getBalance()) {
			System.out.println("Cannot withdraw.");
			return false;
		}
		this.balance -= amount;
		return true;
	}
	boolean deposit(double amount) {
		if(amount < 0) {
			System.out.println("Cannot deposit.");
			return false;
		}
		this.balance += amount;
		return true;
	}
	public double futureValue(int year) {
		return getBalance() * Math.pow(1 + getInterestRate(), year);
	}
	String writeToString() {
		DecimalFormat interestFormat = new DecimalFormat("#.####");
		DecimalFormat noDecimalPlaces = new DecimalFormat("#");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		return getAccountNumber() + "," + noDecimalPlaces.format(getBalance()) + "," 
				+ interestFormat.format(getInterestRate()) + "," 
				+ dateFormatter.format(getOpenedOn());
	}
	public static void addTransaction(Transaction transaction) {
		listOfTransactions.add(transaction);
	}
	public static List<Transaction> getTransactions(){
		return listOfTransactions;
	}
}
