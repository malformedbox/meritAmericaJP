package com.meritamerica.assignment6.model.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.meritamerica.assignment6.model.AccountHolder;
import com.meritamerica.assignment6.model.CDAccount;
import com.meritamerica.assignment6.model.CheckingAccount;
import com.meritamerica.assignment6.model.MeritBank;
import com.meritamerica.assignment6.model.SavingsAccount;
import com.meritamerica.assignment6.model.exceptions.DoesNotExistException;
import com.meritamerica.assignment6.model.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment6.model.exceptions.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment6.model.exceptions.NegativeAmountException;
import com.meritamerica.assignment6.model.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment6.model.repository.AccountHolderRepository;
import com.meritamerica.assignment6.model.repository.CDAccountRepository;
import com.meritamerica.assignment6.model.repository.CheckingAccountRepository;
import com.meritamerica.assignment6.model.repository.SavingsAccountRepository;

import DTO.AccountHolderDTO;
import DTO.CDAccountDTO;
import DTO.CheckingAccountDTO;
import DTO.SavingsAccountDTO;

@Service
public class AccountHolderServices {
	private final static Logger LOGGER = 
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	AccountHolderRepository accRepository;
	@Autowired
	AccountHoldersContactDetailsServices ahDetailService;
	@Autowired
	CheckingAccountRepository checkingRepository;
	@Autowired
	SavingsAccountRepository savingsRepository;
	@Autowired
	CDAccountRepository cdRepository;
	
	public AccountHolderServices() {}

	public List<AccountHolder> getAllAccounts(){
		return accRepository.findAll();
	}
	public AccountHolder addAccount(AccountHolderDTO accDTO) throws NoSuchResourceFoundException {
		if(accDTO.getFirstName().isBlank() || accDTO.getLastName().isBlank() || accDTO.getSsn().isBlank()) {
			throw new NoSuchResourceFoundException("First name, last name, or SSN are incorrect."); 
		}
		AccountHolder newAcc = new AccountHolder(accDTO.getFirstName(), accDTO.getMiddleName(), 
				accDTO.getLastName(), accDTO.getSsn());
		return accRepository.save(newAcc);
	}
	public AccountHolder getAccountById(Long id) throws DoesNotExistException {
		return accRepository.findById(id).orElse(null);
	}
	//===================================================================================================
	public List<CheckingAccount> getCheckingAccountById(Long id) 
			throws DoesNotExistException{
		AccountHolder ah = ahDetailService.getAccountHolderById(id);
		return checkingRepository.findAllByaccHolder(ah);
	}
	public CheckingAccount addChecking(CheckingAccountDTO checkingDTO, Long id) 
			throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException, DoesNotExistException {
		if(checkingDTO.getBalance() < 0) {
			LOGGER.log(Level.SEVERE, "Balance below 0");
			throw new NoSuchResourceFoundException("Invalid balance.");
		}
		/*
		if(checkingDTO.getBalance() + ah.getCombinedBalance() > 250000) {
			throw new NoSuchResourceFoundException("Balance exceeds 250,000 limit.");
		}*/
		CheckingAccount newChecking = new CheckingAccount(checkingDTO.getBalance());
		return checkingRepository.save(newChecking);
	}
	//===================================================================================================
	public List<SavingsAccount> getSavingsAccountById(@PathVariable Long id) 
			throws DoesNotExistException{
		AccountHolder ah = ahDetailService.getAccountHolderById(id);
		return savingsRepository.findAllByaccHolder(ah);
	}
	public SavingsAccount addSavings(SavingsAccountDTO savingsDTO, @PathVariable Long id) 
			throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException, DoesNotExistException {
		if(id > MeritBank.listOfAccounts.size() - 1 || id < 0) {
			throw new DoesNotExistException("No account holder exists with the given ID.");
		}
		if(savingsDTO.getBalance() < 0) {
			LOGGER.log(Level.SEVERE, "Balance below 0");
			throw new NoSuchResourceFoundException("Invalid balance.");
		}
		/*
		if(savingsDTO.getBalance() + ah.getCombinedBalance() > 250000) {
			throw new NoSuchResourceFoundException("Balance exceeds 250,000 limit.");
		}*/
		SavingsAccount newSavings = new SavingsAccount(savingsDTO.getBalance());
		return savingsRepository.save(newSavings);		
	}
	//===================================================================================================

	public List<CDAccount> getCDAccountById(@PathVariable Long id) 
			throws DoesNotExistException{
		AccountHolder ah = ahDetailService.getAccountHolderById(id);
		return cdRepository.findAllByaccHolder(ah);
	}
	public CDAccount addCD(@RequestBody @Valid CDAccountDTO cdDTO, @PathVariable Long id) 
			throws NoSuchResourceFoundException, ExceedsFraudSuspicionLimitException, NegativeAmountException, DoesNotExistException {
		if(id > MeritBank.listOfAccounts.size() - 1 || id < 0) {
			throw new DoesNotExistException("No account holder exists with the given ID.");
		}
		if(cdDTO.getBalance() < 0) {
			throw new NegativeAmountException("Balance cannot be negative.");
		}
		if(cdDTO.getInterestRate() <= 0) {
			throw new NegativeAmountException("Interest rate cannot be negative or missing.");
		}
		if(cdDTO.getTerm() < 1) {
			throw new NegativeAmountException("Invalid term.");
		}
		CDAccount newCD = new CDAccount(cdDTO.getCdOffering(), cdDTO.getBalance());
		return cdRepository.save(newCD);
	}
}
