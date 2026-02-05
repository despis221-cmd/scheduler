package org.example.scheduler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice(basePackages = "org.example.scheduler")
public class ExceptionsHandler {

    private static final String ERROR_KEY = "error";

    // 404 Not Found
    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleScheduleNotFoundException(ScheduleNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 401 Unauthorized
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPasswordException(InvalidPasswordException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // 400 Bad Request
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR_KEY, message);
        return new ResponseEntity<>(errors, status);
    }

}