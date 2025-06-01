package com.example.books_social.application.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AccountDto(
    @NotBlank
    @Email
    String email,
    @NotBlank
    String username,
    @NotBlank
    String userPhoto,
    @NotBlank
    String password
){
}
