package com.example.books_social.application.shared.exceptions;

public class UnauthenticatedUserException extends Exception {

    public UnauthenticatedUserException() {
        super();
    }

    public UnauthenticatedUserException(String message) {
        super(message);
    }

    public UnauthenticatedUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthenticatedUserException(Throwable cause) {
        super(cause);
    }
}
