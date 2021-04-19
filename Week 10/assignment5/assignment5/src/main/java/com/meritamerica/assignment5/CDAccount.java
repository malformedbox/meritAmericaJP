package com.meritamerica.assignment5;

import java.text.*;
import java.util.Date;

public class CDAccount extends BankAccount {
	private int term;
	private CDOffering cdOffering;
	
	public CDAccount() {} //Default constructor
	public CDAccount(CDOffering offering, double balance) {
		super(balance, offering.getInterestRate(), System.currentTimeMillis());
		//this.term = offering.getTerm();
	}
	CDAccount(long accountNumber, double balance, 
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
	public CDOffering getcdOffering() { //get"cdOffering" will be what is displayed on the json
		int index = 0;
		for(int i = 0; i < MeritBank.listOfOfferingsAL.size(); i++) {
			if(this.term == MeritBank.listOfOfferingsAL.get(i).getTerm()) {
				index = i;
			}
		}
		return MeritBank.listOfOfferingsAL.get(index);
	}
	public void setOffering(CDOffering cdOffering) {
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
	static CDAccount readFromString(String accountData) throws ParseException{
		String[] parts = accountData.split(",\\s*");
		//SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if(accountData.length() > 0 && parts.length == 5) {
			return new CDAccount(Long.parseLong(parts[0]), 
					Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), 
					Long.parseLong(parts[3]), Integer.parseInt(parts[4]));
		}else {
			throw new NumberFormatException();
		}
	}
	@Override
	String writeToString() {
		//10,5000,0.025,01/01/2020,10
		DecimalFormat interestFormat = new DecimalFormat("#.####");
		DecimalFormat noDecimalPlaces = new DecimalFormat("#");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		return getAccountNumber() + "," + noDecimalPlaces.format(getBalance()) + ","
				+ interestFormat.format(getInterestRate()) + ","
				+ dateFormatter.format(getOpenedOn());
	}
}