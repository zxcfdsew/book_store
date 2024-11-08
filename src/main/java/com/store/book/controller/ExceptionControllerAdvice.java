package com.store.book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> authenticationException(AuthenticationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
