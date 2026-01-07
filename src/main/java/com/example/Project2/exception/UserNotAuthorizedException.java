package com.example.Project2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
@ResponseStatus(HttpStatus.UNAUTHORIZED)

public class UserNotAuthorizedException extends AccessDeniedException {
    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
