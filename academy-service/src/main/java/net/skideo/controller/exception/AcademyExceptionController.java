package net.skideo.controller.exception;

import net.skideo.exception.AcademyAlreadyExistsException;
import net.skideo.exception.AcademyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AcademyExceptionController {

    @ExceptionHandler(value = AcademyAlreadyExistsException.class)
    public ResponseEntity<?> academyAlreadyExists() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Academy already exists");
    }

    @ExceptionHandler(value = AcademyNotFoundException.class)
    public ResponseEntity<?> academyNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Academy not found");
    }
}
