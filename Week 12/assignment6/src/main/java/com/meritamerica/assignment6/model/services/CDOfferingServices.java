package com.meritamerica.assignment6.model.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment6.model.BankAccount;
import com.meritamerica.assignment6.model.CDOffering;
import com.meritamerica.assignment6.model.MeritBank;
import com.meritamerica.assignment6.model.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment6.model.repository.CDOfferingRepository;

import DTO.CDOfferingDTO;

@Service
public class CDOfferingServices {
	@Autowired
	CDOfferingRepository cdOfferingRepository;
	
	public List<CDOffering> getAllCDOffers(){
		return cdOfferingRepository.findAll();
	}
	
	public CDOffering addCDOffering(CDOfferingDTO cdOfferDTO) throws NoSuchResourceFoundException {
		if(cdOfferDTO.getInterestRate() <= 0 || cdOfferDTO.getInterestRate() >= 1) {
			throw new NoSuchResourceFoundException("Incorrect interest rate.");
		}
		if(cdOfferDTO.getTerm() < 1) {
			throw new NoSuchResourceFoundException("Incorrect term.");
		}
		//MeritBank.listOfOfferingsAL.add(cdOffer);
		CDOffering newCDOffer = new CDOffering(cdOfferDTO.getTerm(), cdOfferDTO.getInterestRate());
		return cdOfferingRepository.save(newCDOffer);
	}
}
