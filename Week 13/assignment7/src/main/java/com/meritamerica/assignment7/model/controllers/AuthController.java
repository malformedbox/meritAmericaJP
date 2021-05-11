package com.meritamerica.assignment7.model.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.model.User;
import com.meritamerica.assignment7.model.services.AuthService;

import DTO.LoginRequest;
import DTO.SignupRequest;

@CrossOrigin
@RestController
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	///POST /Authenticate/CreateUser
	@PostMapping("/Authenticate/CreateUser")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createUser(@RequestBody @Valid SignupRequest signupRequest) {
		return authService.createUser(signupRequest);	
	}
	
	//POST /Authenticate
	@PostMapping("/Authenticate")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) throws Exception{
		return authService.authenticateUser(loginRequest);
	}
	
	@GetMapping
	@PostMapping("/")
	public String defaultTest() {
		return("<h1>Welcome Default</h1>");
	}
}
