package com.meritamerica.assignment6.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cdOffering")
public class CDOffering {	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cdOffer_id")
	private Long id;
	
	@OneToMany(mappedBy="cdOffering", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<CDAccount> cdOffer = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*
	public CDAccount getCdAccount() {
		return cdAccount;
	}
	public void setCdAccount(CDAccount cdAccount) {
		this.cdAccount = cdAccount;
	}*/
	private double interestRate;
	private int term;	

	public CDOffering() {}
	public CDOffering(int term, double interestRate) {
		this.interestRate = interestRate;
		this.term = term;	
	}
	static CDOffering readFromString(String cdOfferingDataString){
		String [] parts = cdOfferingDataString.split(",\\s*");
		
		if(cdOfferingDataString.length() > 0 && parts.length == 2) {
			return new CDOffering(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]));
			
		} else {
			throw new NumberFormatException();
		}
	}
	
	public int getTerm(){
		return this.term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public double getInterestRate() {
		return this.interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public String writeToString() {
		return getTerm() + "," + getInterestRate();
	}
}
