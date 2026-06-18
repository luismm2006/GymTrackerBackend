package com.GymTrackerBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason="Conflict")
public class Conflict extends RuntimeException{
	public Conflict() {
        super("Conflict");
    }

    public Conflict(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Conflict(String message, Throwable cause) {
        super(message, cause);
    }

    public Conflict(String message) {
        super(message);
    }

    public Conflict(Throwable cause) {
        super(cause);
    }
}
