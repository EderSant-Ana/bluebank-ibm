package com.santana.bluebank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailInvalidoException extends Exception {

    public EmailInvalidoException(String message) {
        super(message);
    }
}
