package com.meritamerica.assignment6.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "accountHolder_details")
public class AccountHoldersContactDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String username;
	
	@JsonIgnore
	@NotBlank
	@Size(max = 120)
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accHolder_id")
	private AccountHolder accHolder;
	
	public AccountHoldersContactDetails() {}
	public AccountHoldersContactDetails(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public AccountHolder getAccHolder() {
		return accHolder;
	}
	public void setAccHolder(AccountHolder accHolder) {
		this.accHolder = accHolder;
	}
}
