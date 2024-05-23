package com.assigment.adminservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomException extends ResponseStatusException {
    public CustomException(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }
}
