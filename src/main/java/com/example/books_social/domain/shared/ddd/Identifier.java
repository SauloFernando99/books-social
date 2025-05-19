package com.example.books_social.domain.shared.ddd;

public interface Identifier <T>{
    Notification validate();
    T value();
}
