package com.meritamerica.assignment5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //404
public class DoesNotExistException extends Exception {
	public DoesNotExistException(String msg) {
		super(msg);
	}
}
