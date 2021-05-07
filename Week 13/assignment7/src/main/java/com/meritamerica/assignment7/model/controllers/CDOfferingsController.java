package com.meritamerica.assignment7.model.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.model.CDOffering;
import com.meritamerica.assignment7.model.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment7.model.services.CDOfferingServices;

import DTO.CDOfferingDTO;

@RestController
@RequestMapping("/CDOfferings")
public class CDOfferingsController {	
	@Autowired
	CDOfferingServices cdService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CDOffering> getOfferings(){	return cdService.getAllCDOffers();}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering addCDOffering(@RequestBody @Valid CDOfferingDTO cdOfferDTO) throws NoSuchResourceFoundException {
		return cdService.addCDOffering(cdOfferDTO);
	}
}
