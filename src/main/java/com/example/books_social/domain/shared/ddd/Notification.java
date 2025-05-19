package com.example.books_social.domain.shared.ddd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notification {

    private final List<Error> errors = new ArrayList<>();

    public void addError(String message) {
        errors.add(new Error(message, null));
    }

    public void addError(String message, Exception cause) {
        errors.add(new Error(message, cause));
    }

    public boolean hasNoErrors() {
        return errors.isEmpty();
    }

    public String message() {
        return errors.stream()
                .map(Error::getMessage)
                .collect(Collectors.joining(" | "));
    }

    public List<Error> getErrors() {
        return errors;
    }

    public static class Error {
        private final String message;
        private final Exception cause;

        public Error(String message, Exception cause) {
            this.message = message;
            this.cause = cause;
        }

        public String getMessage() {
            return message;
        }

        public Exception getCause() {
            return cause;
        }
    }
}
