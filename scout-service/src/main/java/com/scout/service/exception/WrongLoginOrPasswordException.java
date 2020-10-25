package com.scout.service.exception;

public class WrongLoginOrPasswordException extends RuntimeException {

    public WrongLoginOrPasswordException(String message) {
        super(message);
    }
}
