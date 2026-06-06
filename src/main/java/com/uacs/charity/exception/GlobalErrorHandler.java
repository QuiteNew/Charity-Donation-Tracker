package com.uacs.charity.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    // 1. Handles the 400 Bad Request (including your @DecimalMin for positive donations)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
    
    // 2. Handles the 409 Conflict (Strictly for Closed Campaigns)
    @ExceptionHandler(BusinessInvariantException.class)
    public ResponseEntity<Map<String, String>> handleInvariant(BusinessInvariantException e) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Conflict");
        body.put("message", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}