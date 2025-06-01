package com.example.books_social.presentation.account.controller;

import java.util.UUID;

public class LoginResponse {
    private String token;
    private String email;
    private String username;
    private String userPhoto;
    private UUID id;

    public LoginResponse(String token, String email, String username, String userPhoto, UUID id) {
        this.token = token;
        this.email = email;
        this.username = username;
        this.userPhoto = userPhoto;
        this.id = id;
    }
    public UUID getId() {
        return id;
    }
    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPhoto() {
        return userPhoto;
    }
}
