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

    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
    
    
    @ExceptionHandler(BusinessInvariantException.class)
    public ResponseEntity<Map<String, String>> handleInvariant(BusinessInvariantException e) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Conflict");
        body.put("message", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}