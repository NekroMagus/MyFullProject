package net.skideo.controller.exception;

import net.skideo.exception.AlreadyExistsException;
import net.skideo.exception.NotFoundException;
import net.skideo.exception.WrongLoginOrPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionController {

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<?> alreadyExists() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Already exists");
    }

    //@ExceptionHandler(value = NotFoundException.class)
    //public ResponseEntity<?>  notFound() {
    //    return ResponseEntity
    //            .status(HttpStatus.NOT_FOUND)
    //            .body("Auth not found");
    //}

    @ExceptionHandler(value = IllegalAccessException.class)
    public ResponseEntity<?> illegalAccessException() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Service role and user role differ");
    }

    @ExceptionHandler(value = WrongLoginOrPasswordException.class)
    public ResponseEntity<?> wrongLoginOrPassword() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Wrong login or password");
    }

}