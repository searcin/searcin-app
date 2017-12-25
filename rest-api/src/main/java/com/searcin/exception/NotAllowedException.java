package com.searcin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NotAllowedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotAllowedException() {
		super();
	}

	public NotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAllowedException(String message) {
		super(message);
	}

	public NotAllowedException(Throwable cause) {
		super(cause);
	}
}
