package net.skideo.controller.exception;

import net.skideo.exception.NotFoundException;
import net.skideo.exception.AlreadyExistsException;
import net.skideo.exception.ApiError;
import net.skideo.exception.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiError> exists(AlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(createApiError(e, HttpStatus.CONFLICT));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> illegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(createApiError(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> notFound(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(createApiError(e, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> forbidden(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(createApiError(e, HttpStatus.FORBIDDEN));
    }


    private ApiError createApiError(Exception e, HttpStatus httpStatus) {
        return ApiError.builder()
                .message(e.getMessage())
                .error(httpStatus.getReasonPhrase())
                .statusCode(httpStatus.value())
                .build();
    }
}
