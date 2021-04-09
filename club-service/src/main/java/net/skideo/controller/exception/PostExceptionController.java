package net.skideo.controller.exception;

import net.skideo.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostExceptionController {

    @ExceptionHandler(value = PostNotFoundException.class)
    public ResponseEntity<?> postNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Post not found");
    }

}
