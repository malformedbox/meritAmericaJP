package com.meritamerica.assignment7.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String username;
	
	@JsonIgnore
	@NotBlank
	@Size(max = 120)
	private String password;
	
	private boolean active;
	private String role;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accHolder_id")
	private AccountHolder accHolder;
	
	public User() {}
	public User(String role, String username, String password) {
		this.role = role;
		this.username = username;
		this.password = password;
	}
	
}
