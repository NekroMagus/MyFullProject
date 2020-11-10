package net.skideo.controller;

import net.skideo.exception.ScoutNotFoundException;
import net.skideo.exception.ScoutsAlreadyExistsException;
import net.skideo.exception.WrongLoginOrPasswordException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ScoutsAlreadyExistsException.class)
    public String scoutsAlreadyExists() {
        return "Scout already exists";
    }

    @ExceptionHandler(value = WrongLoginOrPasswordException.class)
    public String wrongLoginOrPassword() {
        return "Wrong login or password";
    }

    @ExceptionHandler(value = ScoutNotFoundException.class)
    public String scoutNotFound() {
        return "Scout not found";
    }
}
