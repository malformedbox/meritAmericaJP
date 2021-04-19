package com.meritamerica.assignment5.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment5.BankAccount;
import com.meritamerica.assignment5.CDOffering;
import com.meritamerica.assignment5.MeritBank;
import com.meritamerica.assignment5.exceptions.NoSuchResourceFoundException;

@Service
public class CDOfferingServices {
	
	@Autowired
	CDOffering offers;
	
	public List<CDOffering> getAllCDOffers(){
		return MeritBank.listOfOfferingsAL;
	}
	
	public CDOffering addCDOffering(CDOffering cdOffer) throws NoSuchResourceFoundException {
		if(cdOffer.getInterestRate() <= 0 || cdOffer.getInterestRate() >= 1) {
			throw new NoSuchResourceFoundException("Incorrect interest rate.");
		}
		if(cdOffer.getTerm() < 1) {
			throw new NoSuchResourceFoundException("Incorrect term.");
		}
		MeritBank.listOfOfferingsAL.add(cdOffer);
		return(cdOffer);
	}
}
