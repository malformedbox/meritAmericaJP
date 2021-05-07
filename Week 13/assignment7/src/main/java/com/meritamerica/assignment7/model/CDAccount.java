package com.meritamerica.assignment7.model;

import java.text.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.assignment7.model.exceptions.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment7.model.exceptions.NegativeAmountException;

@Entity
public class CDAccount extends BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "accHolder_id")
	@JsonIgnore
	private AccountHolder accHolder;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AccountHolder getAccHolder() {
		return accHolder;
	}
	public void setAccHolder(AccountHolder accHolder) {
		this.accHolder = accHolder;
	}
	
	private int term;	
	@ManyToOne
	@JoinColumn(name = "cdOffering_id")
	private CDOffering cdOffering;

	public CDAccount() {} //Default constructor
	public CDAccount(CDOffering offering, double balance) {
		super(balance, offering.getInterestRate(), System.currentTimeMillis());
		//this.term = offering.getTerm();
	}
	public CDAccount(CDOffering offering, double balance, AccountHolder ah) {
		super(balance, offering.getInterestRate(), System.currentTimeMillis());
		this.accHolder = ah;
	}
	public CDAccount(long accountNumber, double balance, 
			double interestRate, long accountOpenedOn, int term){ //REQUIRED
		super(accountNumber, balance, interestRate, accountOpenedOn);
		this.term = term;
	}
	
	void setTerm(int term) {
		this.term = term;
	}
	int getTerm() {
		return this.term;
	}	
	public CDOffering getCdOffering() { //get"cdOffering" will be what is displayed on the json
		int index = 0;
		for(int i = 0; i < MeritBank.listOfOfferings.size(); i++) {
			if(this.term == MeritBank.listOfOfferings.get(i).getTerm()) {
				index = i;
			}
		}
		return MeritBank.listOfOfferings.get(index);
	}
	public void setCdOffering(CDOffering cdOffering) {
		this.cdOffering = cdOffering;
	}
	
	public long getAccountNumber() {
		return super.accountNumber;
	}

	public double futureValue() throws NegativeAmountException, ExceedsFraudSuspicionLimitException{
		return getBalance() * MeritBank.recursiveFutureValue(1, getTerm(), getInterestRate());
	}
	@Override
	boolean withdraw(double amount) {
		if(amount > 0 && amount <= getBalance()) {
			//&& new Date().getYear() > getOpenedOn().getYear() + getTerm()
			return true;
		} else {
			System.out.println("Cannot withdraw.");
			return false;
		}
	}
	@Override
	boolean deposit(double amount) {
		if(amount > 0) {
			//&& new Date().getYear() > getOpenedOn().getYear() + getTerm()
			return true;
		} else {
			System.out.println("Cannot deposit.");
		return false;
		}
	}
}