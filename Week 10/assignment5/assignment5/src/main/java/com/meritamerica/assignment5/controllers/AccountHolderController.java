package com.meritamerica.assignment5.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.AccountHolder;
import com.meritamerica.assignment5.CDAccount;
import com.meritamerica.assignment5.CDOffering;
import com.meritamerica.assignment5.CheckingAccount;
import com.meritamerica.assignment5.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment5.MeritBank;
import com.meritamerica.assignment5.NegativeAmountException;
import com.meritamerica.assignment5.SavingsAccount;
import com.meritamerica.assignment5.exceptions.DoesNotExistException;
import com.meritamerica.assignment5.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment5.services.AccountHolderServices;

import DTO.CDAccountDTO;
import DTO.CheckingAccountDTO;
import DTO.SavingsAccountDTO;

@RestController
@RequestMapping("/AccountHolders")
public class AccountHolderController {
	@Autowired
	AccountHolderServices ahService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AccountHolder> getAccountHolders(){ return ahService.getAllAccounts();}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAH(@RequestBody @Valid AccountHolder ah) 
			throws NoSuchResourceFoundException {
		return ahService.addAccount(ah);
	}
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountHolder getAccountById(@PathVariable int id) 
			throws DoesNotExistException {
		return ahService.getAccountById(id);
	}
//===================================================================================================
	//POST /AccountHolders/{id}/CheckingAccounts
	@PostMapping("/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody CheckingAccount addCheckingAccount(@RequestBody CheckingAccountDTO checkingDTO, @PathVariable int id) 
			throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException, DoesNotExistException {
		return ahService.addChecking(checkingDTO, id);
	}
	//GET /AccountHolders/{id}/CheckingAccounts
	@GetMapping("/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<CheckingAccount> getCheckingAccount(@PathVariable int id) 
			throws DoesNotExistException, NoSuchResourceFoundException {
		return ahService.getCheckingAccounts(id);
	}
//===================================================================================================
	//POST /AccountHolders/{id}/SavingsAccounts
	@PostMapping("/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody SavingsAccount addSavingsAccount(@RequestBody SavingsAccountDTO savingsDTO, @PathVariable int id) 
			throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException, DoesNotExistException {
		return ahService.addSavings(savingsDTO, id);
	}
	//GET /AccountHolders/{id}/SavingsAccounts
	@GetMapping("/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<SavingsAccount> getSavingsAccount(@PathVariable int id) 
			throws DoesNotExistException, NoSuchResourceFoundException {
		return ahService.getSavingsAccounts(id);
	}
//===================================================================================================
	//POST /AccountHolders/{id}/CDAccounts
	@PostMapping("/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody CDAccount addCDAccount(@RequestBody @Valid CDAccountDTO cdDTO, @PathVariable int id) 
			throws NoSuchResourceFoundException, ExceedsFraudSuspicionLimitException, NegativeAmountException, DoesNotExistException {
		if(id <= MeritBank.listOfAccounts.size()) {
			AccountHolder accountHolder = MeritBank.getAccountHolderById(id);
			CDOffering cdo = MeritBank.getCDOfferingById(cdDTO.getCdOffering().getId());
			CDAccount cda = new CDAccount(cdo, cdDTO.getBalance());
			
			if(cda.getBalance() < 0) {
				throw new NegativeAmountException("Balance cannot be negative.");
			}
			accountHolder.addCDAccount(cda);
			return cda;
		}
		throw new NoSuchResourceFoundException("Invalid id");
	}
	
	//GET /AccountHolders/{id}/CDAccounts
	@GetMapping("/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<CDAccount> getCDAccount(@PathVariable int id) 
			throws DoesNotExistException, NoSuchResourceFoundException {
		return ahService.getCDAccounts(id);
	}
}
