package DTO;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.meritamerica.assignment7.model.AccountHolder;

public class SignupRequest {
	private AccountHolder accountHolder;
	@NotBlank
	@Size(max = 50)
	@Email
	private String username;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String password;
	
	private Set<String> roles;
	
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
}