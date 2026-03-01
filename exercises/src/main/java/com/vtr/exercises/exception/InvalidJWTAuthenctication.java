package com.vtr.exercises.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJWTAuthenctication extends AuthenticationException {
    public InvalidJWTAuthenctication(String message) {
        super(message);
    }
}
