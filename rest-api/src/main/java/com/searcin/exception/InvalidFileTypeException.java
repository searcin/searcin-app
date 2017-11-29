package com.searcin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidFileTypeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidFileTypeException() {
		super();
	}

	public InvalidFileTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidFileTypeException(String message) {
		super(message);
	}

	public InvalidFileTypeException(Throwable cause) {
		super(cause);
	}
}
