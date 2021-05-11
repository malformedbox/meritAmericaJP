package com.meritamerica.assignment7.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.model.AccountHolder;
import com.meritamerica.assignment7.model.User;
import com.meritamerica.assignment7.model.repository.AccountHolderRepository;
import com.meritamerica.assignment7.model.repository.UserRepository;

import DTO.SignupRequest;

@Service
public class UserServices {
	@Autowired
	UserRepository userRepo;
	@Autowired
	AccountHolderRepository ahRepo;
	
	public UserServices() {}
	
	public List<User> getAllDetails(){
		return userRepo.findAll();
	}
	public AccountHolder getAccByUsername(String username) {
		AccountHolder ah = ahRepo.findByUsername(username).orElse(null);
		return ah;
	}
	
	public AccountHolder getAccountHolderById(Long accid) {
		AccountHolder ah = ahRepo.findById(accid).orElse(null);
		return ah;
	}
	public User getAccountHoldersContactDetailsById(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	public User addAccountHoldersContactDetails(SignupRequest ahDetailDTO) {
		User newDetail = new User(ahDetailDTO.getRole(), ahDetailDTO.getUsername(), ahDetailDTO.getPassword());
		return userRepo.save(newDetail);
	}

}
