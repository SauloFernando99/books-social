package com.example.books_social.domain.model.reply;

import com.example.books_social.domain.shared.ddd.Identifier;
import com.example.books_social.domain.shared.ddd.Notification;

import java.util.Objects;
import java.util.UUID;

public class ReplyId implements Identifier<Long> {
    private final UUID value;

    public ReplyId(UUID value) {
        Notification notification = validate(value);
        if (!notification.hasNoErrors()) {
            throw new IllegalArgumentException(notification.message());
        }
        this.value = value;
    }

    private Notification validate(UUID value) {
        Notification notification = new Notification();
        if (value == null) {
            notification.addError("Reply ID must not be null.");
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
        if (!(o instanceof ReplyId that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public UUID getValue() {
        return value;
    }
}
