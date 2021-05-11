package com.meritamerica.assignment7.model;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.model.exceptions.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment7.model.exceptions.NegativeAmountException;

@Service
public class MeritBank {
	private static long accountNumber;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	static ArrayList<String> dataReadFromFile;
	public static List<AccountHolder> listOfAccounts;
	public static Map<Long, BankAccount> mapOfAccounts;
	public static List<CDOffering> listOfOfferings = new ArrayList<>();
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	static void addAccountHolder(AccountHolder accountHolder) {
		MeritBank.listOfAccounts.add(accountHolder);
	}
	static AccountHolder[] sortAccountHolders() {
		AccountHolder[] resultAccountHolder = new AccountHolder[listOfAccounts.size()];
		Collections.sort(listOfAccounts);
		for(int i = 0 ; i < listOfAccounts.size(); i++) {
			resultAccountHolder[i] = listOfAccounts.get(i);
		}
		return resultAccountHolder;
	}
	static void setNextAccountNumber(long nextAccountNumber) {
		accountNumber = nextAccountNumber;
	}
	static long getNextAccountNumber() {
		accountNumber += 1;
		return accountNumber;
	}
	public static AccountHolder getAccountHolderById(int id) {
		return listOfAccounts.get(id);
	}
	static void clearCDOfferings() {
		listOfOfferings = null;
	}
	static List<CDOffering> getCDOfferings() {
		return listOfOfferings;
	}
	public static CDOffering getCDOfferingById(int id) {
		return listOfOfferings.get(id);
	}
	static void setCDOfferings(List<CDOffering> offerings) {
		listOfOfferings = offerings;
	}
	/*
	public static CDOffering getBestCDOffering(double depositAmount) 
			throws NegativeAmountException, ExceedsFraudSuspicionLimitException{
		if(listOfOfferings != null) {
	        double stored = futureValue(depositAmount, listOfOfferings[0].getInterestRate(), listOfOfferings[0].getTerm());
	        int indexBiggest = 0;
	        for(int i = 1; i < listOfOfferings.length; i++)
	        {
	            double tempStored = futureValue(depositAmount, listOfOfferings[i].getInterestRate(), listOfOfferings[i].getTerm());
	            if(tempStored > stored)
	            {
	                stored = tempStored;
	                indexBiggest = i;
	            }
	        }
	        return listOfOfferings[indexBiggest];
		}
        return null;
	}
	public static CDOffering getSecondBestCDOffering(double depositAmount) 
			throws NegativeAmountException, ExceedsFraudSuspicionLimitException{
		if(listOfOfferings != null) {
	        double biggest = futureValue(depositAmount, listOfOfferings[0].getInterestRate(), listOfOfferings[0].getTerm());
	        double secondBiggest = futureValue(depositAmount, listOfOfferings[0].getInterestRate(), listOfOfferings[0].getTerm());
	        int indexBiggest = 0;
	        int indexSecondBiggest = 0;
	        for(int i = 1; i < listOfOfferings.length; i++)
	        {
	            double tempStored = futureValue(depositAmount, listOfOfferings[i].getInterestRate(), listOfOfferings[i].getTerm());
	            if(tempStored > biggest)
	            {
	                indexSecondBiggest = indexBiggest;
	                indexBiggest = i;
	            }
	            if(tempStored > secondBiggest && tempStored != biggest)
	            {
	                indexSecondBiggest = i;
	            }
	        }
	        return listOfOfferings[indexSecondBiggest];
		}
		return null;
	}
	*/
	public static double totalBalances() {//Total balances of all the account holders, checking,savings,cd balance
		double totalBalance = 0;
		for(AccountHolder allAccounts : listOfAccounts) {
			if(allAccounts != null) {
				totalBalance += allAccounts.getCombinedBalance();
			}		
		}
		return totalBalance;
	}
	public static double futureValue(double presentValue, double interestRate, int term) 
			throws NegativeAmountException, ExceedsFraudSuspicionLimitException{
		return presentValue * recursiveFutureValue(1, term, interestRate);
	}
	public static double recursiveFutureValue(double amount, int years, double interestRate) {
		if (years != 0) {
			return (amount + interestRate) * recursiveFutureValue(amount, years - 1, interestRate);
		} else {
			return 1;
		}				
	}
}

