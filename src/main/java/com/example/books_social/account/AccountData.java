package com.example.books_social.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AccountData(
    @NotBlank
    @Email
    String email,
    @NotBlank
    String password

){
}
