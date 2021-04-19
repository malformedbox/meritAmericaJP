package com.meritamerica.assignment5;

import java.text.*;

public class SavingsAccount extends BankAccount{
	
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
