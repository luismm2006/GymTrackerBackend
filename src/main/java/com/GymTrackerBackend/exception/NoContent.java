package com.GymTrackerBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NO_CONTENT, reason="NoContent")
public class NoContent extends RuntimeException{
	public NoContent() {
        super("NoContent");
    }

    public NoContent(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoContent(String message, Throwable cause) {
        super(message, cause);
    }

    public NoContent(String message) {
        super(message);
    }

    public NoContent(Throwable cause) {
        super(cause);
    }
}
