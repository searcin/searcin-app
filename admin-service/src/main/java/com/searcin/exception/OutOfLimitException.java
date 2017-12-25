package com.searcin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE)
public class OutOfLimitException extends RuntimeException  {
	private static final long serialVersionUID = 1L;

	public OutOfLimitException() {
		super();
	}

	public OutOfLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public OutOfLimitException(String message) {
		super(message);
	}

	public OutOfLimitException(Throwable cause) {
		super(cause);
	}
}
