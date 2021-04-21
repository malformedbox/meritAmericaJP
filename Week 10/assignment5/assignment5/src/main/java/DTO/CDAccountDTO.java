package DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.meritamerica.assignment5.CDOffering;

public class CDAccountDTO {

	private double balance;
	private CDOffering cdOffering;
	private int id;
	private double interestRate;
	private int term;

	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public CDOffering getCdOffering() {
		return cdOffering;
	}
	public void setCdOffering(CDOffering cdOffering) {
		this.cdOffering = cdOffering;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
