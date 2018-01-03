package com.searcin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AwsS3Exception extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AwsS3Exception() {
		super();
	}

	public AwsS3Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public AwsS3Exception(String message) {
		super(message);
	}

	public AwsS3Exception(Throwable cause) {
		super(cause);
	}
}
