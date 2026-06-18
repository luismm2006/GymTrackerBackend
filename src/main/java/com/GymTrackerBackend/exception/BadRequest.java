package com.GymTrackerBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason="Bad request")
public class BadRequest extends RuntimeException{
	public BadRequest() {
        super("Bad request");
    }

    public BadRequest(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BadRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequest(String message) {
        super(message);
    }

    public BadRequest(Throwable cause) {
        super(cause);
    }
}
