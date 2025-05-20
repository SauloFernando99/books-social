package com.example.books_social.domain.model.book;

import com.example.books_social.domain.shared.ddd.Notification;
import com.example.books_social.domain.shared.ddd.ValueObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class CoverUrl extends ValueObject {
    private final String value;

    public CoverUrl (String value) {
        this.value = value;

        Notification notification = validate();
        if (!notification.hasNoErrors()) {
            throw new IllegalArgumentException(notification.message());
        }
    }

    public String value() {
        return value;
    }

    @Override
    protected Notification validate() {
        Notification notification = new Notification();

        if (value == null || value.isBlank()) {
            notification.addError("Cover URL must not be blank.");
        } else {
            try {
                new URL(value);
            } catch (MalformedURLException e) {
                notification.addError("Cover URL is not a valid URL: " + value);
            }
        }

        return notification;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoverUrl coverUrl)) return false;
        return Objects.equals(value, coverUrl.value);
    }
}
