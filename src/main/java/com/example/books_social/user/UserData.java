package com.example.books_social.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserData (
    @NotBlank
    @Email
    String email,
    @NotBlank
    String password,
    @NotBlank
    String userName,
    @NotBlank
    String userPicture

){
}
