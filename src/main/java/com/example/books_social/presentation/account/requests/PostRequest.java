package com.example.books_social.presentation.account.requests;

public record PostRequest(
    String username,
    String email,
    String password,
    String userPhoto
) {
}
