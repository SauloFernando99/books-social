package com.example.books_social.domain.model.reply;

import com.example.books_social.domain.model.comentary.CommentaryText;
import com.example.books_social.domain.shared.ddd.Notification;
import com.example.books_social.domain.shared.ddd.ValueObject;

import java.util.Objects;

public class ReplyText extends ValueObject {
    private final String value;

    public ReplyText(String value) {
        this.value = value;

        Notification notification = validate();
        if (!notification.hasNoErrors()){
            throw new IllegalArgumentException(notification.message());
        }
    }

    public String value() {
        return value;
    }

    @Override
    protected Notification validate() {
        Notification notification = new Notification();

        if (value.isEmpty()) {
            notification.addError("Reply must not be empty");
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
        if (!(o instanceof ReplyText replyText)) return false;
        return Objects.equals(value, replyText.value);
    }
}
