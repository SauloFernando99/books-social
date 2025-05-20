package com.example.books_social.domain.model.book;

import com.example.books_social.domain.shared.ddd.Notification;
import com.example.books_social.domain.shared.ddd.ValueObject;

import java.util.Objects;

public class Rating extends ValueObject {
    private int value;

    public Rating (int value) {
        this.value = value;

        Notification notification = validate();
        if (notification.hasNoErrors()) {
            throw new IllegalArgumentException(notification.message());
        }
    }

    public int value() {
        return value;
    }

    @Override
    protected Notification validate() {
        Notification notification = new Notification();

        if (value > 5) {
            notification.addError("Rating must not be over 5");
        }
        if (value < 1) {
            notification.addError("Rating must not be bellow 1");
        }

        return notification;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating rating)) return false;
        return Objects.equals(value, rating.value);
    }
}
