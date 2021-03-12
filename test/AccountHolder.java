package com.meritamerica.assignment3;

import java.util.ArrayList;

public class AccountHolder implements Comparable<AccountHolder>{
	private static String firstName;
	private static String middleName; 
	private static String lastName;
	private static String ssn;
	private static final double BALANCE_LIMIT = 250000;
	
	private static ArrayList<CheckingAccount> listOfCheckingAccounts = new ArrayList<CheckingAccount>();
	private static ArrayList<SavingsAccount> listOfSavingsAccounts = new ArrayList<SavingsAccount>();
	private static ArrayList<CDAccount> listOfCDAccounts = new ArrayList<CDAccount>();
	
	public AccountHolder() {} //Default constructor
	//This constructor might be unnecessary in this assignment
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		AccountHolder.firstName = firstName;
		AccountHolder.middleName = middleName; 
		AccountHolder.lastName = lastName; 
		AccountHolder.ssn = ssn;
	}
	static String getFirstName() {
		return AccountHolder.firstName;
	}
	static void setFirstName(String firstname) {
		AccountHolder.firstName = firstname;
	}
	static String getMiddleName() {
		return AccountHolder.middleName;
	}
	static void setMiddleName(String middleName) {
		AccountHolder.middleName = middleName;
	}
	static String getLastName() {
		return AccountHolder.lastName;
	}
	static void setLastName(String lastName) {
		AccountHolder.lastName = lastName;
	}
	static String getSSN() {
		return AccountHolder.ssn;
	}
	void setSSN(String ssn) {
		AccountHolder.ssn = ssn;
	}
	static AccountHolder readFromString(String accountHolderData) throws Exception{
		String[] parts = accountHolderData.split(",\\s*");
		
		/*AccountHolder.lastName = parts[0];
		AccountHolder.middleName = parts[1];
		AccountHolder.firstName = parts[2];
		AccountHolder.ssn = parts[3];*/

		return new AccountHolder(parts[0], parts[1], parts[2], parts[3]);
	}
	/*
	public CheckingAccount addCheckingAccount(double openingBalance) {
		if((openingBalance + getCombinedBalance() - getCDBalance()) < BALANCE_LIMIT) {
			return addCheckingAccount(new CheckingAccount(openingBalance));	
		}
		System.out.println("Cannot add any more checking accounts. "
				+ "Combined balances from Checking and Savings "
				+ "account exceed the limit of $" + BALANCE_LIMIT);
		return null;	
	}
	 This method is used when addCheckingAccount passes the conditions for
	 opening more accounts.
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
		if((checkingAccount.getBalance() + getCombinedBalance() - getCDBalance()) < BALANCE_LIMIT) {
			for(int i = 0; i < listOfCheckingAccounts.length; i++) {
				if(listOfCheckingAccounts[i] == null) {
					listOfCheckingAccounts[i] = checkingAccount;
					break;
				}
			}
		}
		return null;
	}
	public CheckingAccount[] getCheckingAccounts() {
		return this.listOfCheckingAccounts;
	}
	public int getNumberOfCheckingAccounts() {
		int numberOfCheckingAccounts = 0;
		for(CheckingAccount i : listOfCheckingAccounts) {
			if(i!=null) {
				numberOfCheckingAccounts++;
			}
		}
		return numberOfCheckingAccounts;
	}
	public double getCheckingBalance() {
		double balance = 0;
		for(int i = 0; i < listOfCheckingAccounts.length; i++) {
			if(listOfCheckingAccounts[i] != null) {
				balance += listOfCheckingAccounts[i].getBalance();
			}
		}
		return balance;
	}
	public double getCombinedBalance() { //Total of Checking, Savings, CDBalance
		double combinedBalance = getCheckingBalance() + getSavingsBalance() + getCDBalance();		
		return combinedBalance;
	}*/
	@Override
	public int compareTo(AccountHolder ah) {
		//sort by combined balances of their accounts
		return 0;
	}
	
	static String writeToString() {
		if(getMiddleName().equals("")) {
			return getFirstName() + " " + getLastName() + " " + getSSN();
		}
		return getFirstName() + " " + getMiddleName() + " " + getLastName() + " " + getSSN();
	}

}
