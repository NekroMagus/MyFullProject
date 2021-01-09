package net.skideo.controller.exception;

import net.skideo.exception.AlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionController {

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<?> academyAlreadyExists() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Already exists");
    }

}