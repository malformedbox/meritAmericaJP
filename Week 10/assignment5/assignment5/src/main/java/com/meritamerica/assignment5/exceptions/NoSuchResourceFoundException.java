package com.meritamerica.assignment5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) //400
public class NoSuchResourceFoundException extends Exception {
	public NoSuchResourceFoundException(String msg) {
		super(msg);
	}
}
