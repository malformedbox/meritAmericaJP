package com.meritamerica.assignment6.model.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment6.model.AccountHoldersContactDetails;
import com.meritamerica.assignment6.model.services.AccountHoldersContactDetailsServices;

import DTO.AccountHoldersContactDetailDTO;

@RestController
public class AccountHoldersContactDetailsController {
	
	@Autowired
	AccountHoldersContactDetailsServices ahDetailService;
	
	@GetMapping("/ahdetails")
	public List<AccountHoldersContactDetails> getAllDetails(){
		return ahDetailService.getAllDetails();
	}
	
	@GetMapping("/ahdetails/{id}")
	public AccountHoldersContactDetails getAccountHoldersContactDetails(@PathVariable Long id) {
		return ahDetailService.getAccountHoldersContactDetailsById(id);
	}
	
	@PostMapping("/ahdetails")
	public AccountHoldersContactDetails addAccountHoldersContactDetails(@RequestBody AccountHoldersContactDetailDTO ahDetailDTO) {
		return ahDetailService.addAccountHoldersContactDetails(ahDetailDTO);
	}
}
