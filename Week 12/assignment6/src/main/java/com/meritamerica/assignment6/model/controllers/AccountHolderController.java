package com.meritamerica.assignment6.model.controllers;

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

import com.meritamerica.assignment6.model.AccountHolder;
import com.meritamerica.assignment6.model.CDAccount;
import com.meritamerica.assignment6.model.CDOffering;
import com.meritamerica.assignment6.model.CheckingAccount;
import com.meritamerica.assignment6.model.MeritBank;
import com.meritamerica.assignment6.model.SavingsAccount;
import com.meritamerica.assignment6.model.exceptions.DoesNotExistException;
import com.meritamerica.assignment6.model.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment6.model.exceptions.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment6.model.exceptions.NegativeAmountException;
import com.meritamerica.assignment6.model.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment6.model.services.AccountHolderServices;

import DTO.AccountHolderDTO;
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
	public AccountHolder addAH(@RequestBody @Valid AccountHolderDTO ahDTO) 
			throws NoSuchResourceFoundException {
		return ahService.addAccount(ahDTO);
	}
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountHolder getAccountById(@PathVariable Long id) 
			throws DoesNotExistException {
		return ahService.getAccountById(id);
	}
//===================================================================================================
	//POST /AccountHolders/{id}/CheckingAccounts
	@PostMapping("/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody CheckingAccount addCheckingAccount(@RequestBody CheckingAccountDTO checkingDTO, @PathVariable Long id) 
			throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException, DoesNotExistException {
		return ahService.addChecking(checkingDTO, id);
	}
	//GET /AccountHolders/{id}/CheckingAccounts
	@GetMapping("/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<CheckingAccount> getCheckingAccount(@PathVariable Long id) 
			throws DoesNotExistException, NoSuchResourceFoundException {
		return ahService.getCheckingAccountById(id);
	}
//===================================================================================================
	//POST /AccountHolders/{id}/SavingsAccounts
	@PostMapping("/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody SavingsAccount addSavingsAccount(@RequestBody SavingsAccountDTO savingsDTO, @PathVariable Long id) 
			throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException, DoesNotExistException {
		return ahService.addSavings(savingsDTO, id);
	}
	//GET /AccountHolders/{id}/SavingsAccounts
	@GetMapping("/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<SavingsAccount> getSavingsAccount(@PathVariable Long id) 
			throws DoesNotExistException, NoSuchResourceFoundException {
		return ahService.getSavingsAccountById(id);
	}
//===================================================================================================
	//POST /AccountHolders/{id}/CDAccounts
	@PostMapping("/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody CDAccount addCDAccount(@RequestBody @Valid CDAccountDTO cdDTO, @PathVariable Long id) 
			throws NoSuchResourceFoundException, ExceedsFraudSuspicionLimitException, NegativeAmountException, DoesNotExistException {
		return ahService.addCD(cdDTO, id);
	}
	
	//GET /AccountHolders/{id}/CDAccounts
	@GetMapping("/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<CDAccount> getCDAccount(@PathVariable Long id) 
			throws DoesNotExistException, NoSuchResourceFoundException {
		return ahService.getCDAccountById(id);
	}
}
