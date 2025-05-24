package com.example.books_social.domain.model.comentary;

import com.example.books_social.domain.model.book.CoverUrl;
import com.example.books_social.domain.shared.ddd.Notification;
import com.example.books_social.domain.shared.ddd.ValueObject;

import java.util.Objects;

public class Progress extends ValueObject {
    private final Integer value;

    public Progress(Integer value) {
        this.value = value;

        Notification notification = validate();
        if (!notification.hasNoErrors()){
            throw new IllegalArgumentException(notification.message());
        }
    }

    public Integer value() {
        return value;
    }

    @Override
    protected Notification validate() {
        Notification notification = new Notification();

        if (value < 0) {
            notification.addError("Progress shouldn't be under 0%");
        }
        if (value > 100) {
            notification.addError("Progress shouldn't be over 100%");
        }

        return notification;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Progress progress)) return false;
        return Objects.equals(value, progress.value);
    }
}
