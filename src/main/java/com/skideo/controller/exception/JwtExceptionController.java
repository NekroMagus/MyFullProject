package com.skideo.controller.exception;

import com.skideo.exception.JwtExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtExceptionController {

    @ExceptionHandler(value = JwtExpiredException.class)
    public ResponseEntity<?> jwtExpired(){
        return new ResponseEntity<>("JWT Token has expired", HttpStatus.UNAUTHORIZED);
    }
}