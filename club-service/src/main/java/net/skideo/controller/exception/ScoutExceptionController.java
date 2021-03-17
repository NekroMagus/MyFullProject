package net.skideo.controller.exception;

import net.skideo.exception.ScoutNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ScoutExceptionController {

    @ExceptionHandler(value = ScoutNotFoundException.class)
    public ResponseEntity<?> scoutNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Scout not found");
    }

}
