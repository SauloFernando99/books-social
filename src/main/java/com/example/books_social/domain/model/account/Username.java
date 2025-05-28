package com.example.books_social.domain.model.account;

import com.example.books_social.domain.shared.ddd.Notification;
import com.example.books_social.domain.shared.ddd.ValueObject;
import org.springframework.data.mongodb.core.aggregation.StringOperators.RegexMatch;

public class Username extends ValueObject {
    private final String value;

    public Username (String value) {
        this.value = value;

        Notification notification = validate();

        if (!notification.hasNoErrors()) {
            throw new IllegalArgumentException(notification.message());
        }
    }

    @Override
    protected Notification validate() {
        Notification notification = new Notification();

        if (value.isBlank()){
            notification.addError("Username must not be blank");
        } else if (!value.matches("[a-zA-Z0-9-_]*")) {
            notification.addError("Username must contain only letters and numbers, dashes and underscores! You sent: "
            + value + ".");
        }
        return notification;
    }

    @Override
    public String toString() {
        return value;
    }
}
