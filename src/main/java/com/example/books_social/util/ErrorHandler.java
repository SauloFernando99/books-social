package com.example.books_social.util;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler (EntityNotFoundException.class)
    public ResponseEntity handleCode404(){
        return ResponseEntity.notFound().build();
    }
}
