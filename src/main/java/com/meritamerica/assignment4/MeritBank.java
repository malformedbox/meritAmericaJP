package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class MeritBank {
private static long accountNumber;
	
	static ArrayList<String> dataReadFromFile;
	static ArrayList<AccountHolder> listOfAccounts;
	private static CDOffering[] listOfOfferings;
	
	static boolean readFromFile(String fileName) {
		//Should also read BankAccount transactions and the FraudQueue
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = "";
            dataReadFromFile = new ArrayList<String>();
            while((line = reader.readLine()) != null){
                if(line.length() > 0){
                    dataReadFromFile.add(line);
                }
            }         
            int index = 0;
            listOfOfferings = new CDOffering[Integer.parseInt(dataReadFromFile.get(1))];
            for(int i = 1; i < Integer.parseInt(dataReadFromFile.get(1)); i++) {
            	if(dataReadFromFile.get(i).length() == 1) {
            		int j = i;
            		for(int addCD = 0; addCD < Integer.parseInt(dataReadFromFile.get(j)); addCD++) {
            			i++;
            			listOfOfferings[addCD] = CDOffering.readFromString(dataReadFromFile.get(i));
            		}
            	}
            	i = i + 1;
            	index = i;
            }
        	if(dataReadFromFile.get(index).length() == 1) { //index is 5
        		listOfAccounts = new ArrayList<AccountHolder>();
        		int amountAH = index;
        		for(int addAH = 0; addAH < Integer.parseInt(dataReadFromFile.get(amountAH)); addAH++) {
        			index++; //index is 6
        			//listOfAccounts.add(addAH, AccountHolder.readFromString(dataReadFromFile.get(index)));
        			addAccountHolder(AccountHolder.readFromString(dataReadFromFile.get(index)));
        			index++; //index is 7
        			int j = index;
        			for(int addChecking = 0; addChecking < Integer.parseInt(dataReadFromFile.get(j)); addChecking++) {
        				index++; //index is 8
        				listOfAccounts.get(addAH).addCheckingAccount(CheckingAccount.readFromString(dataReadFromFile.get(index)));
        			}
        			index++; //index is 9  
        			int k = index;
        			for(int addSavings = 0; addSavings < Integer.parseInt(dataReadFromFile.get(k)); addSavings++) {
        				index++;
        				listOfAccounts.get(addAH).addSavingsAccount(SavingsAccount.readFromString(dataReadFromFile.get(index)));
        			}
        			index++; //index should be at 12
        			int l = index;
        			for(int addCD = 0; addCD < Integer.parseInt(dataReadFromFile.get(l)); addCD++) {
        				index++;
        				listOfAccounts.get(addAH).addCDAccount(CDAccount.readFromString(dataReadFromFile.get(index)));
        			}
        		}
        	}
        	setNextAccountNumber(CDAccount.readFromString(dataReadFromFile.get(dataReadFromFile.size()-1)).getAccountNumber());
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

	static AccountHolder[] sortAccountHolders() { //Required method
		AccountHolder[] resultAccountHolder = new AccountHolder[listOfAccounts.size()];
		Collections.sort(listOfAccounts);
		for(int i = 0 ; i < listOfAccounts.size(); i++) {
			resultAccountHolder[i] = listOfAccounts.get(i);
		}
		return resultAccountHolder;
	}
	
	static void setNextAccountNumber(long nextAccountNumber) { //REQUIRED METHOD
		accountNumber = nextAccountNumber;
	}
	static long getNextAccountNumber() {
		accountNumber += 1;
		return accountNumber;
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
	public static CDOffering getBestCDOffering(double depositAmount) {
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
	
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
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
	public static double futureValue(double presentValue, double interestRate, int term) {
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
		/*i.If transaction does not violate any constraints, 
		  	deposit/withdraw values from the relevant BankAccounts 
		  	and add the transaction to the relevant BankAccounts
		ii. If the transaction violates any of the basic constraints 
			(negative amount, exceeds available balance) 
			the relevant exception should be thrown and the processing should terminate
		iii. If the transaction violates the $1,000 suspicion limit, 
			it should simply be added to the FraudQueue for future processing
		 */
		return false;
	}
	public static FraudQueue getFraudQueue() {
		return null;
	}
	public static BankAccount getBankAccount(long accountId) {
		//Return null if account not found
		return null;
	}

}
