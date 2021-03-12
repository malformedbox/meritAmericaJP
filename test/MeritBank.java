package com.meritamerica.assignment3;

import java.io.*;
import java.util.ArrayList;

public class MeritBank {
	private long accountNumber;
	
	static ArrayList<String> dataReadFromFile = new ArrayList<String>();
	static ArrayList<AccountHolder> listOfAccounts = new ArrayList<AccountHolder>();
	static ArrayList<CDOffering> listOfOfferings = new ArrayList<CDOffering>();
	
	//Reads the entire txt file and adds each line as a string to an arraylist
	static boolean readFromFile(String fileName) { //REQUIRED METHOD ========================
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = "";
            while((line = reader.readLine()) != null){
                if(line.length() > 0){
                    dataReadFromFile.add(line);
                }
            } 

            listOfAccounts.add(new AccountHolder("Doe","","John","1234567890"));
            listOfAccounts.add(new AccountHolder("Doe","S","Jane","0987654321"));
            System.out.println(listOfAccounts.get(0).writeToString());
            System.out.println(listOfAccounts.get(1).writeToString());
            
            return true;
        } catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        
	}
	private static String returnAtThisIndex(int index) {
		return dataReadFromFile.get(index);
	}
	static void addAccountHolder(AccountHolder accountHolder) {
		MeritBank.listOfAccounts.add(accountHolder);
	}
	private int getValueOf(String toInt) {
		return Integer.parseInt(toInt);
	}
	
	private int returnIndex(int currentIndex, int indexToMove) {
		return currentIndex + indexToMove;
	}
	static void printAccountList() { //DELETE. This method is for testing purposes to see if all the lines were added to arraylist
		for(AccountHolder print : listOfAccounts) {
			System.out.println(print.writeToString());
		}
	}
	//This should take the info from when the program is run and write over the targeted text file
	static boolean writeToFile(String fileName) { //REQUIRED METHOD ========================
		return false;
	}

	//I think this method is supposed to sort the array in ASCENDING order by the account holder's total balance 
	static AccountHolder[] sortAccountHolders() { //Required method ========================
		return null;
	}
	//Checks the file and last index for what is the last account number. If last index happens to indicate the account number is 10, then this would set the next acc# to 11
	static void setNextAccountNumber(long nextAccountNumber) { //REQUIRED METHOD ========================
		
	}
	//This should check the last index of the file to see what is the account number
	static long getNextAccountNumber() {
		return 0;
	}
	
	static ArrayList<CDOffering> getCDOfferings(){
		return listOfOfferings;
	}
	static void setCDOfferings(CDOffering offering) {
		listOfOfferings.add(offering);
	}
	/* static CDOffering getBestCDOffering(double depositAmount){
	 * 
	 * }
	 * static CDOffering getSecondBestCDOffering(double depositAmount){
	 * 
	 * )
	 */

	/*	public static double totalBalances() {//Total balances of all the account holders, checking,savings,cd balance
		double totalBalance = 0;
		for(AccountHolder allAccounts : listOfAccounts) {
			if(allAccounts != null) {
				totalBalance += allAccounts.getCombinedBalance();
			}		
		}
		return totalBalance;
	}
	public static double futureValue(double presentValue, double interestRate, int term) {
		return presentValue * Math.pow(1 + interestRate, term);
	}
	*/
}
