package com.meritamerica.assignment7.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.meritamerica.assignment7.model.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment7.model.exceptions.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment7.model.exceptions.NegativeAmountException;

import lombok.Data;

@Data
@Entity
@Table(name = "accountHolders") //if not declared, will just take the class's name
public class AccountHolder implements Comparable<AccountHolder>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	
	private String firstName;
	private String middleName; 
	private String lastName;
	private String ssn;
	
	private String username;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	private static final double BALANCE_LIMIT = 250000;
	
	@OneToMany(mappedBy="accHolder", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<CheckingAccount> listOfCheckingAccounts = new ArrayList<CheckingAccount>();
	@OneToMany(mappedBy="accHolder", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<SavingsAccount> listOfSavingsAccounts = new ArrayList<SavingsAccount>();
	@OneToMany(mappedBy="accHolder", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<CDAccount> listOfCDAccounts = new ArrayList<CDAccount>();

	public AccountHolder() {}
	public AccountHolder(String firstName, String middleName, String lastName, String ssn, User user) {
		this.firstName = firstName;
		this.middleName = middleName; 
		this.lastName = lastName; 
		this.ssn = ssn;
		this.user = user;
		username = user.getUsername();
	}
	/*
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	*/
	public User getAccountHoldersContactDetails() {
		return user;
	}
	public void setAccountHoldersContactDetails (User ahDetails) {
		this.user = ahDetails;
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
	public List<CheckingAccount> getListOfCheckingAccounts() {
		return listOfCheckingAccounts;
	}
	public void setListOfCheckingAccounts(List<CheckingAccount> listOfCheckingAccounts) {
		this.listOfCheckingAccounts = listOfCheckingAccounts;
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
	public List<SavingsAccount> getListOfSavingsAccounts() {
		return listOfSavingsAccounts;
	}
	public void setListOfSavingsAccounts(List<SavingsAccount> listOfSavingsAccounts) {
		this.listOfSavingsAccounts = listOfSavingsAccounts;
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
	public List<CDAccount> getListOfCDAccounts() {
		return listOfCDAccounts;
	}
	public void setListOfCDAccounts(List<CDAccount> listOfCDAccounts) {
		this.listOfCDAccounts = listOfCDAccounts;
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
}
