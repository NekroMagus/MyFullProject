package net.skideo.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClubExceptionController {

    @ExceptionHandler(value = ClassNotFoundException.class)
    public ResponseEntity<?> clubNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Club not found");
    }

}
