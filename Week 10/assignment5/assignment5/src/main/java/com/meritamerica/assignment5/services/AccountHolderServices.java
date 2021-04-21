package com.meritamerica.assignment5.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.meritamerica.assignment5.AccountHolder;
import com.meritamerica.assignment5.CDAccount;
import com.meritamerica.assignment5.CheckingAccount;
import com.meritamerica.assignment5.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment5.MeritBank;
import com.meritamerica.assignment5.NegativeAmountException;
import com.meritamerica.assignment5.SavingsAccount;
import com.meritamerica.assignment5.exceptions.DoesNotExistException;
import com.meritamerica.assignment5.exceptions.NoSuchResourceFoundException;

import DTO.CDAccountDTO;
import DTO.CheckingAccountDTO;
import DTO.SavingsAccountDTO;

@Service
public class AccountHolderServices {
	private final static Logger LOGGER = 
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	AccountHolder ah;
	
	public List<AccountHolder> getAllAccounts(){
		return MeritBank.listOfAccounts;
	}
	public AccountHolder addAccount(AccountHolder account) throws NoSuchResourceFoundException {
		if(account.getFirstName().isBlank()) {
			throw new NoSuchResourceFoundException("First name, last name, or SSN are incorrect."); 
		}
		MeritBank.listOfAccounts.add(account);
		return account;
	}
	public AccountHolder getAccountById(@PathVariable int id) throws DoesNotExistException {
		if(id > MeritBank.listOfAccounts.size() - 1 || id < 0) {
			throw new DoesNotExistException("No account holder exists with the given ID.");
		}
		return MeritBank.listOfAccounts.get(id);
	}
	//===================================================================================================
	public List<CheckingAccount> getCheckingAccounts(@PathVariable int id) 
			throws DoesNotExistException{
		if(id > MeritBank.listOfAccounts.size() - 1 || id < 0) {
			throw new DoesNotExistException("No account holder exists with the given ID.");
		}
		return MeritBank.listOfAccounts.get(id).getCheckingAccounts();
	}
	public CheckingAccount addChecking(CheckingAccountDTO checkingDTO, @PathVariable int id) 
			throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException, DoesNotExistException {
		if(id > MeritBank.listOfAccounts.size() - 1 || id < 0) {
			throw new DoesNotExistException("No account holder exists with the given ID.");
		}
		if(checkingDTO.getBalance() < 0) {
			LOGGER.log(Level.SEVERE, "Balance below 0");
			throw new NoSuchResourceFoundException("Invalid balance.");
		}
		if(checkingDTO.getBalance() + ah.getCombinedBalance() > 250000) {
			throw new NoSuchResourceFoundException("Balance exceeds 250,000 limit.");
		}
		return MeritBank.listOfAccounts.get(id).addCheckingAccount(checkingDTO.getBalance());
	}
	//===================================================================================================
	public List<SavingsAccount> getSavingsAccounts(@PathVariable int id) 
			throws DoesNotExistException{
		if(id > MeritBank.listOfAccounts.size() - 1 || id < 0) {
			throw new DoesNotExistException("No account holder exists with the given ID.");
		}
		return MeritBank.listOfAccounts.get(id).getSavingsAccounts();
	}
	public SavingsAccount addSavings(SavingsAccountDTO savingsDTO, @PathVariable int id) 
			throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException, DoesNotExistException {
		if(id > MeritBank.listOfAccounts.size() - 1 || id < 0) {
			throw new DoesNotExistException("No account holder exists with the given ID.");
		}
		if(savingsDTO.getBalance() < 0) {
			LOGGER.log(Level.SEVERE, "Balance below 0");
			throw new NoSuchResourceFoundException("Invalid balance.");
		}
		if(savingsDTO.getBalance() + ah.getCombinedBalance() > 250000) {
			throw new NoSuchResourceFoundException("Balance exceeds 250,000 limit.");
		}
		return MeritBank.listOfAccounts.get(id).addSavingsAccount(savingsDTO.getBalance());
	}
	//===================================================================================================

	public List<CDAccount> getCDAccounts(@PathVariable int id) 
			throws DoesNotExistException{
		if(id > MeritBank.listOfAccounts.size() - 1 || id < 0) {
			throw new DoesNotExistException("No account holder exists with the given ID.");
		}
		return MeritBank.listOfAccounts.get(id).getCDAccounts();
	}
	public CDAccount addCD(@RequestBody CDAccountDTO cdDTO, @PathVariable int id) 
			throws NoSuchResourceFoundException, ExceedsFraudSuspicionLimitException, NegativeAmountException, DoesNotExistException {
		if(id > MeritBank.listOfAccounts.size() - 1 || id < 0) {
			throw new DoesNotExistException("No account holder exists with the given ID.");
		}
		if(cdDTO.getBalance() < 0) {
			throw new NegativeAmountException("Cannot deposit a negative amount or missing.");
		}
		if(cdDTO.getInterestRate() <= 0) {
			throw new NegativeAmountException("Interest rate cannot be negative or missing.");
		}
		if(cdDTO.getTerm() < 1) {
			throw new NegativeAmountException("Invalid term.");
		}
		return MeritBank.listOfAccounts.get(id).addCDAccount(MeritBank.listOfOfferingsAL.get(id), cdDTO.getBalance());
	}
}
