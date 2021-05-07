package com.meritamerica.assignment7.model;

import java.text.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class CheckingAccount extends BankAccount {
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
	
	public CheckingAccount() {}
	public CheckingAccount(double openingBalance) {
		super(openingBalance, 0.0001);	
	}
	public CheckingAccount(double balance, AccountHolder ah) {
		super(balance, 0.0001);
		this.accHolder = ah;
	}
	public CheckingAccount(long accountNumber, double balance, 
			double interestRate, long accountOpenedOn){
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}
	
	static CheckingAccount readFromString(String accountData) throws ParseException{
		String[] parts = accountData.split(",\\s*");
		//SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if(accountData.length() > 0 && parts.length == 4) {
			return new CheckingAccount(Long.parseLong(parts[0]), 
					Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Long.parseLong(parts[3]));
		}else {
			throw new NumberFormatException();
		}
	}
}