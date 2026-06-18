package com.GymTrackerBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="NotFound")
public class NotFound extends RuntimeException{
	public NotFound() {
        super("NotFound");
    }

    public NotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFound(String message) {
        super(message);
    }

    public NotFound(Throwable cause) {
        super(cause);
    }
}
