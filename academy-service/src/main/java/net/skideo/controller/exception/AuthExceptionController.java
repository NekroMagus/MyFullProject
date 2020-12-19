package net.skideo.controller.exception;

import net.skideo.exception.WrongLoginOrPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionController {

    @ExceptionHandler(value = WrongLoginOrPasswordException.class)
    public ResponseEntity<?> wrongLoginOrPassword() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Wrong login or password");
    }

}
