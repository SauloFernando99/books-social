package com.example.books_social.presentation.shared.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {

    private final int code;
    private final String message;
    private final String detail;
    private final LocalDateTime timestamp;
    private final Class<?> developerMessage;

    public ErrorMessage(HttpStatus status, Throwable throwable) {
        this.code = status.value();
        this.message = status.getReasonPhrase();
        this.detail = throwable.getMessage();
        this.timestamp = generateTimestamp();
        this.developerMessage = throwable.getClass();
    }

    private LocalDateTime generateTimestamp() {
        return LocalDateTime.now();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Class<?> getDeveloperMessage() {
        return developerMessage;
    }
}