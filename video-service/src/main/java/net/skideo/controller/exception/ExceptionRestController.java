package net.skideo.controller.exception;

import net.skideo.exception.AlreadyRatedException;
import net.skideo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionRestController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException notFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(notFoundException.getMessage());
    }

    @ExceptionHandler(value = AlreadyRatedException.class)
    public ResponseEntity<?> alreadyRatedException(AlreadyRatedException alreadyRatedException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(alreadyRatedException.getMessage());
    }

}
