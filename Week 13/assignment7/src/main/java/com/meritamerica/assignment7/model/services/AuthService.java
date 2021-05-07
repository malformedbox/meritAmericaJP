package com.meritamerica.assignment7.model.services;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.model.Role;
import com.meritamerica.assignment7.model.User;
import com.meritamerica.assignment7.model.enums.RoleEnum;
import com.meritamerica.assignment7.model.exceptions.ApiNotFoundException;
import com.meritamerica.assignment7.model.repository.RoleRepository;
import com.meritamerica.assignment7.model.repository.UserRepository;

import DTO.SignupRequest;

@Service
public class AuthService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public ResponseEntity<?> createUser(@Valid SignupRequest signupRequest) {
		if(userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(String.format("Username %s already exists", signupRequest.getUsername()));
		}
		
		User user = new User(signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()));
		Set<String> strRoles = signupRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		
		if(strRoles == null) {
			throw new ApiNotFoundException("Role not found");
		} else {
			strRoles.forEach(role -> {
				switch (role.toLowerCase()) {
				case "admin":
					Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN).orElseThrow(() -> new ApiNotFoundException("Role not found"));
				case "user":
					Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER).orElseThrow(() -> new ApiNotFoundException("Role not found"));
				default:
					throw new ApiNotFoundException("Role not found");
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return new ResponseEntity<>("User created", HttpStatus.CREATED);
	}

}