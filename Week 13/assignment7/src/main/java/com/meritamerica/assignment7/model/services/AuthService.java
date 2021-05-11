package com.meritamerica.assignment7.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.model.AuthResponse;
import com.meritamerica.assignment7.model.User;
import com.meritamerica.assignment7.model.security.jwt.JwtUtil;
import com.meritamerica.assignment7.model.security.service.UserDetailsServiceImpl;
import com.meritamerica.assignment7.model.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import DTO.LoginRequest;
import DTO.SignupRequest;

@Service
public class AuthService {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	public ResponseEntity<?> createUser(SignupRequest signupRequest) {
		if(userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(String.format("Username %s already exists", signupRequest.getUsername()));
		}
		
		User user = new User(signupRequest.getRole(),signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()));
		
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> authenticateUser (LoginRequest loginRequest) throws Exception{
//			Authentication auth = authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//			
//			SecurityContextHolder.getContext().setAuthentication(auth);
//			String jwt = jwtUtil.generateJwtToken(auth);
//			
//			UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
//			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
//			
//			return ResponseEntity.ok( new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));	
			
			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
				);
			}
			catch (BadCredentialsException e) {
				throw new Exception("Incorrect username or password", e);
			}

			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(loginRequest.getUsername());

			final String jwt = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new AuthResponse(jwt));
	}
}
