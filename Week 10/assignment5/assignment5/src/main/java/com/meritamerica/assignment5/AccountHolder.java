package com.meritamerica.assignment5;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class AccountHolder implements Comparable<AccountHolder>{
	static int nextAcc = 1;
	int id;
	private String firstName;
	private String middleName; 
	private String lastName;
	private String ssn;
	private static final double BALANCE_LIMIT = 250000;
	
	private ArrayList<CheckingAccount> listOfCheckingAccounts = new ArrayList<CheckingAccount>();
	private ArrayList<SavingsAccount> listOfSavingsAccounts = new ArrayList<SavingsAccount>();
	private ArrayList<CDAccount> listOfCDAccounts = new ArrayList<CDAccount>();
	
	public AccountHolder() {}
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.id = nextAcc++;
		this.firstName = firstName;
		this.middleName = middleName; 
		this.lastName = lastName; 
		this.ssn = ssn;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}
	public String getMiddleName() {
		return this.middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSSN() {
		return this.ssn;
	}
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}
	
	static AccountHolder readFromString(String accountHolderData) throws Exception{
		String[] parts = accountHolderData.split(",\\s*");
		if(accountHolderData.length() > 0 && parts.length == 4) {
			return new AccountHolder(parts[2], parts[1], parts[0], parts[3]);
		}else {
			System.out.println("AH offering");
			throw new Exception();
		}	
	}	

	public CheckingAccount addCheckingAccount(double openingBalance) 
			throws ExceedsCombinedBalanceLimitException{
		if((openingBalance + getCombinedBalance() - getCDBalance()) < BALANCE_LIMIT) {
			return addCheckingAccount(new CheckingAccount(openingBalance));	
		}else if((openingBalance + getCombinedBalance() - getCDBalance()) > BALANCE_LIMIT) {
			throw new ExceedsCombinedBalanceLimitException("Exceeded combined balance limit.");
		}
		return null;	
	}
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) 
			throws ExceedsCombinedBalanceLimitException{
		if((checkingAccount.getBalance() + getCombinedBalance() - getCDBalance()) < BALANCE_LIMIT) {
			listOfCheckingAccounts.add(checkingAccount);
			return checkingAccount;
		} else if((getCombinedBalance() - getCDBalance()) > BALANCE_LIMIT) {
			throw new ExceedsCombinedBalanceLimitException("Exceeded combined balance limit.");
		}
		return checkingAccount;
	}
	public ArrayList<CheckingAccount> getCheckingAccounts() {
		return listOfCheckingAccounts;
	}
	public int getNumberOfCheckingAccounts() {
		return listOfCheckingAccounts.size();
	}
	public double getCheckingBalance() {
		double balance = 0;
		for(int i = 0; i < listOfCheckingAccounts.size(); i++) {
			balance += listOfCheckingAccounts.get(i).getBalance();
		}
		return balance;
	}
	public double getCombinedBalance() { //Total of Checking, Savings, CDBalance
		double combinedBalance = getCheckingBalance() + getSavingsBalance() + getCDBalance();		
		return combinedBalance;
	}
	public SavingsAccount addSavingsAccount(double openingBalance) 
			throws ExceedsCombinedBalanceLimitException{
		if((openingBalance + getCombinedBalance() - getCDBalance()) < BALANCE_LIMIT) {
			return addSavingsAccount(new SavingsAccount(openingBalance));	
		}else if((openingBalance + getCombinedBalance() - getCDBalance()) > BALANCE_LIMIT) {
			throw new ExceedsCombinedBalanceLimitException("Exceeded combined balance limit.");
		}
		return null;	
	}
	SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) 
			throws ExceedsCombinedBalanceLimitException{
		if((savingsAccount.getBalance() + getCombinedBalance() - getCDBalance()) < BALANCE_LIMIT) {
			listOfSavingsAccounts.add(savingsAccount);
			return savingsAccount;
		} else if((getCombinedBalance() - getCDBalance()) > BALANCE_LIMIT) {
			throw new ExceedsCombinedBalanceLimitException("Exceeded combined balance limit.");
		}	
		return savingsAccount;
	}
	public ArrayList<SavingsAccount> getSavingsAccounts() {
		return listOfSavingsAccounts;
	}
	public int getNumberOfSavingsAccounts() {
		return listOfSavingsAccounts.size();
	}
	public double getSavingsBalance() {
		double balance = 0;
		for(int i = 0; i < listOfSavingsAccounts.size(); i++) {
			balance += listOfSavingsAccounts.get(i).getBalance();
		}
		return balance;
	}
	public CDAccount addCDAccount(CDOffering offering, double openingBalance) 
			throws ExceedsFraudSuspicionLimitException, 
			NegativeAmountException {
		/*if(openingBalance > 1000) {
			throw new ExceedsFraudSuspicionLimitException("Opening balance exceeds 1000.");
		} else */if(openingBalance < 0) {
			throw new NegativeAmountException("Cannot deposit a negative amount.");
		} else {
			return addCDAccount(new CDAccount(offering, openingBalance));
		}
	}
	public CDAccount addCDAccount(CDAccount cdAccount){
		listOfCDAccounts.add(cdAccount);
		return cdAccount;
	}
	public ArrayList<CDAccount> getCDAccounts() {
		return listOfCDAccounts;
	}
	public int getNumberOfCDAccounts() {
		return listOfCDAccounts.size();
	}
	public double getCDBalance() {
		double balance = 0;
		for(int i = 0; i < listOfCDAccounts.size(); i++) {
			balance += listOfCDAccounts.get(i).getBalance();
		}
		return balance;
	}	
	@Override
	public int compareTo(AccountHolder ah) {
		return (int) (this.getCombinedBalance()-((AccountHolder) ah).getCombinedBalance());
	}
	String writeToString() {
		/*StringBuilder sb = new StringBuilder(getLastName()).append(",").append(getMiddleName()).append(",").append(getFirstName()).append(",").append(getSSN()).append(System.lineSeparator());
		sb.append(this.getNumberOfCheckingAccounts()).append(System.lineSeparator());
		
		for(CheckingAccount checking : this.listOfCheckingAccounts) {
			sb.append(checking.writeToString()).append(System.lineSeparator());
			sb.append(checking.getTransactions().size()).append(System.lineSeparator());
			for(Transaction transaction : checking.getTransactions()) sb.append(transaction.writeToString()).append(System.lineSeparator());
		}
		for(SavingsAccount savings : this.listOfSavingsAccounts) {
			sb.append(savings.writeToString()).append(System.lineSeparator());
			sb.append(savings.getTransactions().size()).append(System.lineSeparator());
			for(Transaction transaction : savings.getTransactions()) sb.append(transaction.writeToString()).append(System.lineSeparator());
		}
		for(CDAccount cd : this.listOfCDAccounts) {
			sb.append(cd.writeToString()).append(System.lineSeparator());
			sb.append(cd.getTransactions().size()).append(System.lineSeparator());
			for(Transaction transaction : cd.getTransactions()) sb.append(transaction.writeToString()).append(System.lineSeparator());
		}
		return sb.toString();*/
		return "";
	}
}
