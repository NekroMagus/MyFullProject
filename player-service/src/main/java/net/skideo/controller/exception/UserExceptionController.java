package net.skideo.controller.exception;

import net.skideo.exception.UserExistsException;
import net.skideo.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionController {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> userNotFound(UserNotFoundException e) {
        return new ResponseEntity<>("User was not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFound(UsernameNotFoundException e) {
        return new ResponseEntity<>("Username, email or phone is invalid", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserExistsException.class)
    public ResponseEntity<?> userExists(UserExistsException e) {
        return new ResponseEntity<>("User is already exists", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
