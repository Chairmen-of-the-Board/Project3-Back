package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.NOT_ACCEPTABLE, reason = "Email already exists")


public class DuplicateEmailFoundException extends RuntimeException{
    public DuplicateEmailFoundException() {
    }

    public DuplicateEmailFoundException(String message) {
        super(message);
    }

    public DuplicateEmailFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateEmailFoundException(Throwable cause) {
        super(cause);
    }

    public DuplicateEmailFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
