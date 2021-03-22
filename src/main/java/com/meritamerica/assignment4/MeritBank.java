package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MeritBank {
	private static long accountNumber;
	
	static ArrayList<String> dataReadFromFile;
	static ArrayList<AccountHolder> listOfAccounts;
	static Map<Long, BankAccount> mapOfAccounts;
	private static CDOffering[] listOfOfferings; //Solution test still requires arrays
	
	static boolean readFromFile(String fileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
        	String line = "";
        	dataReadFromFile = new ArrayList<String>();
        	mapOfAccounts = new HashMap<Long, BankAccount>();
        	accountNumber = 0;
        	while((line = reader.readLine()) != null) {
        		if(line.length() > 0) {
        			dataReadFromFile.add(line);
        		}
        	}   	
        	//Set the 3 CD Offerings
        	int numCDOfferings = Integer.parseInt(dataReadFromFile.get(1));
        	//Set the array size for listOfOfferings = amount of CDOfferings available
        	listOfOfferings = new CDOffering[numCDOfferings];
        	int grabAtIndexFromList = 2;
        	for(int addOffering = 0; addOffering < numCDOfferings; addOffering++) {   		
        		if(listOfOfferings[addOffering] == null) {
        			listOfOfferings[addOffering] = CDOffering.readFromString(dataReadFromFile.get(grabAtIndexFromList));
        			grabAtIndexFromList++;
        		}
        	}
        	//Create list of account holders
        	listOfAccounts = new ArrayList<AccountHolder>();
        	int numOfAccountHolders = grabAtIndexFromList;
        	for(int addAH = 0; addAH < Integer.parseInt(dataReadFromFile.get(numOfAccountHolders)); addAH++) {
        		grabAtIndexFromList++; //Should be 6 here, 23 second loop
        		addAccountHolder(AccountHolder.readFromString(dataReadFromFile.get(grabAtIndexFromList)));
        		            		
        		grabAtIndexFromList++; //Should be 7 here, 24
        		int indexForChecking = grabAtIndexFromList;
        		//Add checking accounts
        		for(int addChecking = 0; addChecking < Integer.parseInt(dataReadFromFile.get(indexForChecking)); addChecking++) {
        			grabAtIndexFromList++; //Should be 8 here
        			accountNumber++;
        			listOfAccounts.get(addAH).addCheckingAccount(CheckingAccount.readFromString(dataReadFromFile.get(grabAtIndexFromList)));
        			mapOfAccounts.put(accountNumber, CheckingAccount.readFromString(dataReadFromFile.get(grabAtIndexFromList)));
        			grabAtIndexFromList++;
        			int indexForCheckingTransactions = grabAtIndexFromList;
        			//Add checking transactions
        			for(int checkingTransaction = 0; checkingTransaction < Integer.parseInt(dataReadFromFile.get(indexForCheckingTransactions)); checkingTransaction++) {
        				grabAtIndexFromList++; //Should be 10 here
        					CheckingAccount.addTransaction(
        							Transaction.readFromString(
        									dataReadFromFile.get(grabAtIndexFromList)));
        			}
        			
        		}
        		grabAtIndexFromList++; //Should be 12 here
        		int indexForSavings = grabAtIndexFromList;
        		//Add savings accounts
        		for(int addSavings = 0; addSavings < Integer.parseInt(dataReadFromFile.get(indexForSavings)); addSavings++) {
        			grabAtIndexFromList++; //Should be 13 here
        			accountNumber++;
        			listOfAccounts.get(addAH).addSavingsAccount(SavingsAccount.readFromString(dataReadFromFile.get(grabAtIndexFromList))); 
        			mapOfAccounts.put(accountNumber, SavingsAccount.readFromString(dataReadFromFile.get(grabAtIndexFromList)));
        			grabAtIndexFromList++; //Should be 14 here
        			int indexForSavingsTransactions = grabAtIndexFromList;
        			//Add savings transactions
        			for(int savingsTransaction = 0; savingsTransaction < Integer.parseInt(dataReadFromFile.get(indexForSavingsTransactions)); savingsTransaction++) {
        				grabAtIndexFromList++; //Should be 15 here
        				SavingsAccount.addTransaction(
    							Transaction.readFromString(
    									dataReadFromFile.get(grabAtIndexFromList)));
        			}
        			
        		}
        		grabAtIndexFromList++; //Should be 22 here
        		int indexForCD = grabAtIndexFromList;
        		//Add CD accounts
        		for(int addCD = 0; addCD < Integer.parseInt(dataReadFromFile.get(indexForCD)); addCD++) {
        			grabAtIndexFromList++;
        			accountNumber++;
        			listOfAccounts.get(addAH).addCDAccount(CDAccount.readFromString(dataReadFromFile.get(grabAtIndexFromList)));
            		mapOfAccounts.put(accountNumber, CDAccount.readFromString(dataReadFromFile.get(grabAtIndexFromList)));
        			grabAtIndexFromList++;
        			//Add CD transactions
        			int indexForCDTransactions = grabAtIndexFromList;
         			for(int cdTransaction = 0; cdTransaction < Integer.parseInt(dataReadFromFile.get(indexForCDTransactions)); cdTransaction++) {
         				grabAtIndexFromList++;
        				CDAccount.addTransaction(Transaction.readFromString(dataReadFromFile.get(grabAtIndexFromList)));
        			}
         			
        		}	
        	}
        	grabAtIndexFromList++; //Should be 48 here
        	int indexForFraud = grabAtIndexFromList;
        	for(int addFraud = 0; addFraud < Integer.parseInt(dataReadFromFile.get(indexForFraud)); addFraud++) {
        		grabAtIndexFromList++;
        		FraudQueue.addTransaction(Transaction.readFromString(dataReadFromFile.get(grabAtIndexFromList)));
        	}
            System.out.println("last acc #: " + accountNumber);
        	MeritBank.setNextAccountNumber(accountNumber);
        	//MeritBank.getNextAccountNumber();
        	return true;
        } catch (Exception e) {
			return false;
		}      
	}
	static void addAccountHolder(AccountHolder accountHolder) {
		MeritBank.listOfAccounts.add(accountHolder);
	}
	static boolean writeToFile(String fileName) {
		//Should also read BankAccount transactions and the FraudQueue
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {	
			writer.write(Long.toString(getNextAccountNumber()));
			writer.newLine();
			writer.write(Integer.toString(listOfOfferings.length));
			for(int i = 0; i < listOfOfferings.length; i++) {
				writer.newLine();
				writer.write(listOfOfferings[i].writeToString());
			}
			writer.newLine();
			writer.write(Integer.toString(listOfAccounts.size()));
			
			for(int i = 0; i < listOfAccounts.size(); i++) {
				writer.newLine();
				writer.write(listOfAccounts.get(i).writeToString());
				writer.newLine();
				writer.write(Integer.toString(listOfAccounts.get(i).getCheckingAccounts().size()));
				for(int j = 0; j < listOfAccounts.get(i).getCheckingAccounts().size(); j++) {
					writer.newLine();
					writer.write(listOfAccounts.get(i).getCheckingAccounts().get(j).writeToString());
				}
				writer.newLine();
				writer.write(Integer.toString(listOfAccounts.get(i).getSavingsAccounts().size()));
				for(int j = 0; j < listOfAccounts.get(i).getSavingsAccounts().size(); j++) {
					writer.newLine();
					writer.write(listOfAccounts.get(i).getSavingsAccounts().get(j).writeToString());
				}
				writer.newLine();
				writer.write(Integer.toString(listOfAccounts.get(i).getCDAccounts().size()));
				for(int j = 0; j < listOfAccounts.get(i).getCDAccounts().size(); j++) {
					writer.newLine();
					writer.write(listOfAccounts.get(i).getCDAccounts().get(j).writeToString());
				}
			}
			return true;
		}catch (Exception e) {
			return false;
		} 
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
		return 11;
	}
	static void clearCDOfferings() {
		listOfOfferings = null;
	}
	static CDOffering[] getCDOfferings() {
		return listOfOfferings;
	}
	static void setCDOfferings(CDOffering[] offerings) {
		listOfOfferings = offerings;
	}
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
	public static boolean processTransaction(Transaction transaction) throws NegativeAmountException, 
	ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException{
		if(transaction instanceof WithdrawTransaction) {
			if(transaction.getAmount() < 0) {
				throw new NegativeAmountException("Amount is a negative value.");
			}
			if(transaction.getAmount() > transaction.getSourceAccount().getBalance()) {
				throw new ExceedsAvailableBalanceException("Insufficient funds.");
			}
			if(transaction.getAmount() > 1000) {
				//FraudQueue.addTransaction(transaction);
				throw new ExceedsFraudSuspicionLimitException("Transaction over 1000, flagged for possible fraud.");
			}
			BankAccount.addTransaction(new WithdrawTransaction(transaction.getTargetAccount(), 
					transaction.getAmount()));
			return true;
		}
		if(transaction instanceof DepositTransaction) {
			
			if(transaction.getAmount() < 0) {
				throw new NegativeAmountException("Amount is a negative value.");
			}
			if(transaction.getTargetAccount().getBalance() + transaction.getAmount() < 0) {
				throw new ExceedsAvailableBalanceException("Insufficient funds.");
			}
			if(transaction.getAmount() > 1000) {
				FraudQueue.addTransaction(transaction);
				throw new ExceedsFraudSuspicionLimitException("Transaction over 1000, flagged for possible fraud.");
			}
			BankAccount.addTransaction(new DepositTransaction(transaction.getTargetAccount(),
					transaction.getAmount()));
			return true;
		}
		if(transaction instanceof TransferTransaction) {
			if(transaction.getAmount() < 0) {
				throw new NegativeAmountException("Amount is a negative value.");
			}
			if(transaction.getAmount() > transaction.getSourceAccount().getBalance()) {
				throw new ExceedsAvailableBalanceException("Insufficient funds from source account.");
			}
			if(transaction.getAmount() > 1000) {
				FraudQueue.addTransaction(transaction);
				throw new ExceedsFraudSuspicionLimitException("Transaction over 1000, flagged for possible fraud.");
			} else {
				TransferTransaction transfer = new TransferTransaction(transaction.getSourceAccount(), 
						transaction.getTargetAccount(), transaction.getAmount());
						transaction.getSourceAccount().withdraw(transaction.getAmount());
						transaction.getTargetAccount().deposit(transaction.getAmount());
				return true;
			}
		}
		return false;
	}
	public static FraudQueue getFraudQueue() {
		return null;
	}
	public static BankAccount getBankAccount(long accountId) {
		/*for(int i = 0; i < listOfAccounts.size(); i++) {
			for(int j = 0; j < listOfAccounts.get(i).getCheckingAccounts().size(); j++) {
				if(listOfAccounts.get(i).getCheckingAccounts().get(j).getAccountNumber() == accountId) {
					return listOfAccounts.get(i).getCheckingAccounts().get(j);
				}
			}	
		}
		for(int i = 0; i < listOfAccounts.size(); i++) {
			for(int j = 0; j < listOfAccounts.get(i).getSavingsAccounts().size(); j++) {
				if(listOfAccounts.get(i).getSavingsAccounts().get(j).getAccountNumber() == accountId) {
					return listOfAccounts.get(i).getSavingsAccounts().get(j);
				}
			}	
		}
		for(int i = 0; i < listOfAccounts.size(); i++) {
			for(int j = 0; j < listOfAccounts.get(i).getCDAccounts().size(); j++) {
				if(listOfAccounts.get(i).getCDAccounts().get(j).getAccountNumber() == accountId) {
					return listOfAccounts.get(i).getCDAccounts().get(j);
				}
			}	
		}*/
		//Return null if account not found
		return mapOfAccounts.get(accountId);
	}

}
