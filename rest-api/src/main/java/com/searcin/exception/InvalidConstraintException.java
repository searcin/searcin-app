package com.searcin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidConstraintException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidConstraintException() {
		super();
	}

	public InvalidConstraintException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidConstraintException(String message) {
		super(message);
	}

	public InvalidConstraintException(Throwable cause) {
		super(cause);
	}
}
