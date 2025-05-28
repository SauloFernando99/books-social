package com.example.books_social.domain.model.account;

import com.example.books_social.domain.shared.ddd.Identifier;
import com.example.books_social.domain.shared.ddd.Notification;

import java.util.UUID;

public class UserAccountId implements Identifier<UUID> {

    private UUID value = UUID.randomUUID();

    public UserAccountId(UUID value) {
        this.value = value;
    }

    @Override
    public Notification validate() {
        return null;
    }

    @Override
    public UUID value() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
