package com.meritamerica.assignment3;

public class CDOffering {
	private static int term;
	private static double interestRate;
	
	public CDOffering(int term, double interestRate) {//Constructor
		CDOffering.term = term;
		CDOffering.interestRate = interestRate;
	}
	/* This method receives a string, from MeritBank presumably, 
	 * parse through this to get: term and interest rate 
	 */
	static CDOffering readFromString(String cdOfferingDataString) throws Exception {
		String [] parts = cdOfferingDataString.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
		
		if(cdOfferingDataString.length() > 0) {
			CDOffering.term = Integer.parseInt(parts[0]);
			CDOffering.interestRate = Double.parseDouble(parts[1]);
			
			return null;
		} else {
			throw new NumberFormatException();
		}
	}
	static int getTerm(){
		return CDOffering.term;
	}
	static double getInterestRate() {
		return CDOffering.interestRate;
	}
	//This method should return a string that is passed back to MeritBank to write to file
	//So the format should be similar to how it was read
	String writeToString() {
		return getTerm() + " year term at " + getInterestRate();
	}
	/* Description
	 * Term
	 * Interest Rate
	 */
}
