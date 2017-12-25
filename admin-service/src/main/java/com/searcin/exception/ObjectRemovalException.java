package com.searcin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ObjectRemovalException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectRemovalException() {
		super();
	}

	public ObjectRemovalException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectRemovalException(String message) {
		super(message);
	}

	public ObjectRemovalException(Throwable cause) {
		super(cause);
	}
}
