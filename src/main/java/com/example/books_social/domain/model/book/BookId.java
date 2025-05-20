package com.example.books_social.domain.model.book;

import com.example.books_social.domain.shared.ddd.Identifier;
import com.example.books_social.domain.shared.ddd.Notification;

import java.util.Objects;
import java.util.UUID;

public class BookId implements Identifier<Long> {
    private final UUID value;

    public BookId(UUID value) {
        Notification notification = validate(value);
        if (!notification.hasNoErrors()) {
            throw new IllegalArgumentException(notification.message());
        }
        this.value = value;
    }

    private Notification validate(UUID value) {
        Notification notification = new Notification();
        if (value == null) {
            notification.addError("Book ID must not be null.");
        }
        return notification;
    }

    @Override
    public Notification validate() {
        return null;
    }

    @Override
    public Long value() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookId that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
