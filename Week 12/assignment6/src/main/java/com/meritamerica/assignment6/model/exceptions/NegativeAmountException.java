package com.meritamerica.assignment6.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) //400
public class NegativeAmountException extends Exception{
	public NegativeAmountException(String errorMessage) {
		super(errorMessage);
	}
}
