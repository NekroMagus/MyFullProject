package net.skideo.controller.exception;

import net.skideo.exception.ApiError;
import net.skideo.exception.ResourceExistsException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResourceExistsException.class)
    public HttpEntity<ApiError> exists(ResourceExistsException e) {
        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .statusCode(HttpStatus.CONFLICT.value())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(apiError);
    }
}
