package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Specified email not found.")
public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException() {
    }
    public EmailNotFoundException(String message) {
        super(message);
    }
}
