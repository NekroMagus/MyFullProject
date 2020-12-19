package net.skideo.controller.exception;

import net.skideo.exception.ClubAlreadyExistsException;
import net.skideo.exception.PostNotFoundException;
import net.skideo.exception.ScoutNotFoundException;
import net.skideo.exception.WrongLoginOrPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClubExceptionController {

    @ExceptionHandler(value = ClubAlreadyExistsException.class)
    public ResponseEntity<?> clubAlreadyExists() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Club already exists");
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    public ResponseEntity<?> clubNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Club not found");
    }

}
