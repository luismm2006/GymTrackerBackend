package com.GymTrackerBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.UNAUTHORIZED, reason="Unauthorized")
public class Unauthorized extends RuntimeException{
	public Unauthorized() {
        super("Unauthorized");
    }

    public Unauthorized(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Unauthorized(String message, Throwable cause) {
        super(message, cause);
    }

    public Unauthorized(String message) {
        super(message);
    }

    public Unauthorized(Throwable cause) {
        super(cause);
    }
}
