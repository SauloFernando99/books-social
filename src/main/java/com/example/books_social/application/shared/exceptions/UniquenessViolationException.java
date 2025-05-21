package com.example.books_social.application.shared.exceptions;

public class UniquenessViolationException extends Exception {

    public UniquenessViolationException() {
        super();
    }

    public UniquenessViolationException(String message) {
        super(message);
    }

    public UniquenessViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniquenessViolationException(Throwable cause) {
        super(cause);
    }
}
