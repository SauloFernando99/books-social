package com.example.books_social.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookData(
        @NotNull
        @NotBlank
        String title,
        @NotNull
        @NotBlank
        String gender,
        @NotNull
        @NotBlank
        LocalDate date,
        @NotNull
        @NotBlank
        LocalDate finished,
        @NotNull
        @NotBlank
        String review,
        @NotNull
        @NotBlank
        String favoriteCharacter,
        @NotNull
        @NotBlank
        String assessment,
        @NotNull
        @NotBlank
        String cover,
        @NotNull
        @NotBlank
        int numberPages,
        @NotNull
        @NotBlank
        String readingStatus,
        @NotNull
        @NotBlank
        String bookType) {

}
