package net.skideo.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {

    private Integer statusCode;
    private String error;
    private String message;

}
