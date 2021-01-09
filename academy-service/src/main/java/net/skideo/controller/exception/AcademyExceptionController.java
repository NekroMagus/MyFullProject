package net.skideo.controller.exception;

import net.skideo.exception.AcademyNotFoundException;
import net.skideo.exception.AlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AcademyExceptionController {

    @ExceptionHandler(value = AcademyNotFoundException.class)
    public ResponseEntity<?> academyNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Academy not found");
    }
}
