package com.meritamerica.assignment7.model.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.model.AccountHolder;
import com.meritamerica.assignment7.model.User;
import com.meritamerica.assignment7.model.services.UserServices;

import DTO.SignupRequest;

@RestController
public class UserController {
	
	@Autowired
	UserServices userServices;
	
	@GetMapping("/Me")
	public void getAccByUsername() {
		System.out.println("/me");
		//return userServices.getAccByUsername();
	}
	
	@GetMapping("/ahdetails")
	public List<User> getAllDetails(){
		return userServices.getAllDetails();
	}
	
	@GetMapping("/ahdetails/{id}")
	public User getAccountHoldersContactDetails(@PathVariable Long id) {
		return userServices.getAccountHoldersContactDetailsById(id);
	}
	
	@PostMapping("/ahdetails")
	public User addAccountHoldersContactDetails(@RequestBody SignupRequest ahDetailDTO) {
		return userServices.addAccountHoldersContactDetails(ahDetailDTO);
	}
}
