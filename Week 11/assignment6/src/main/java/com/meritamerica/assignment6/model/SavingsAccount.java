package com.meritamerica.assignment6.model;

import java.text.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class SavingsAccount extends BankAccount{	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "accHolder_id")
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
	
	public SavingsAccount() {}
	public SavingsAccount(double openingBalance) {
		super(openingBalance, 0.01);
	}
	public SavingsAccount(long accountNumber, double balance, 
			double interestRate, long openedOn){ //REQUIRED
		super(accountNumber, balance, interestRate, openedOn);
	}
	
	static SavingsAccount readFromString(String accountData) throws ParseException{
		String[] parts = accountData.split(",\\s*");
		//SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if(accountData.length() > 0 && parts.length == 4) {
			return new SavingsAccount(Long.parseLong(parts[0]), 
					Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Long.parseLong(parts[3]));
		}else {
			throw new NumberFormatException();
		}
	}
}
