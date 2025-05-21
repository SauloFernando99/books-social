package com.example.books_social.presentation.shared.error;

import com.example.books_social.application.shared.exceptions.EntityNotFoundException;
import com.example.books_social.application.shared.exceptions.UnauthenticatedUserException;
import com.example.books_social.application.shared.exceptions.UnauthorizedUserException;
import com.example.books_social.application.shared.exceptions.UniquenessViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

public class ErrorResponseFactory {

    public static ResponseEntity<ErrorMessage> createErrorResponseFrom(Throwable throwable) {
        HttpStatus httpStatus;

        if (throwable instanceof UnauthenticatedUserException) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        } else if (throwable instanceof UnauthorizedUserException) {
            httpStatus = HttpStatus.FORBIDDEN;
        } else if (throwable instanceof EntityNotFoundException || throwable instanceof NoSuchElementException) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (throwable instanceof UniquenessViolationException) {
            httpStatus = HttpStatus.CONFLICT;
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        ErrorMessage errorMessage = new ErrorMessage(httpStatus, throwable);
        return ResponseEntity.status(httpStatus).body(errorMessage);
    }
}
