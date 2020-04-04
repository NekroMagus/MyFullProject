package com.github.skideo.controller.exception;

import com.github.skideo.exception.VideoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VideoExceptionController {

    @ExceptionHandler(value = VideoNotFoundException.class)
    public ResponseEntity<?> videoNotFound(VideoNotFoundException e) {
        return new ResponseEntity<>("Video is not exists", HttpStatus.NOT_FOUND);
    }
}
