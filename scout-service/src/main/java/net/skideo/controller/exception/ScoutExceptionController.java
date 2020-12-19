package net.skideo.controller.exception;

import net.skideo.exception.ScoutNotFoundException;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.exception.ScoutsAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ScoutExceptionController {

    @ExceptionHandler(value = ScoutsAlreadyExistsException.class)
    public ResponseEntity<?> scoutsAlreadyExists() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Scout already exists");
    }

    @ExceptionHandler(value = ScoutNotFoundException.class)
    public ResponseEntity<?> scoutNotFound() {
       return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Scout not found");
    }
}
