package com.meritamerica.assignment5;

import org.springframework.stereotype.Component;

@Component
public class CDOffering {
	static int nextId = 1;
	int id;
	private double interestRate;
	private int term;	

	public CDOffering() {}
	public CDOffering(int term, double interestRate) {
		this.id = nextId++;
		this.interestRate = interestRate;
		this.term = term;	
	}
	static CDOffering readFromString(String cdOfferingDataString){
		String [] parts = cdOfferingDataString.split(",\\s*");
		
		if(cdOfferingDataString.length() > 0 && parts.length == 2) {
			return new CDOffering(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]));
		} else {
			throw new NumberFormatException();
		}
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTerm(){
		return this.term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public double getInterestRate() {
		return this.interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public String writeToString() {
		return getTerm() + "," + getInterestRate();
	}
	/* Description
	 * Term
	 * Interest Rate
	 */
}
