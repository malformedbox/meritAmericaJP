package com.meritamerica.assignment6.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment6.model.AccountHolder;
import com.meritamerica.assignment6.model.AccountHoldersContactDetails;
import com.meritamerica.assignment6.model.repository.AccountHolderRepository;
import com.meritamerica.assignment6.model.repository.AccountHoldersContactDetailsRepository;

import DTO.AccountHoldersContactDetailDTO;

@Service
public class AccountHoldersContactDetailsServices {
	@Autowired
	AccountHoldersContactDetailsRepository ahDetailsRepo;
	@Autowired
	AccountHolderRepository ahRepo;
	
	public AccountHoldersContactDetailsServices() {}
	
	public List<AccountHoldersContactDetails> getAllDetails(){
		return ahDetailsRepo.findAll();
	}
	
	public AccountHolder getAccountHolderById(Long accid) {
		AccountHolder ah = ahRepo.findById(accid).orElse(null);
		return ah;
	}
	public AccountHoldersContactDetails getAccountHoldersContactDetailsById(Long id) {
		return ahDetailsRepo.findById(id).orElse(null);
	}
	public AccountHoldersContactDetails addAccountHoldersContactDetails(AccountHoldersContactDetailDTO ahDetailDTO) {
		AccountHoldersContactDetails newDetail = new AccountHoldersContactDetails(ahDetailDTO.getUsername(), ahDetailDTO.getPassword());
		return ahDetailsRepo.save(newDetail);
	}
}
