package com.example.books_social.application.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookDto(
        @NotNull
        @NotBlank
        String title,
        @NotNull
        @NotBlank
        String author,
        @NotNull
        @NotBlank
        String gender,
        @NotNull
        LocalDate startDate,
        @NotNull
        LocalDate endDate,
        @NotNull
        @NotBlank
        String review,
        @NotNull
        @NotBlank
        String favoriteCharacter,
        @NotNull
        int rating,
        @NotNull
        @NotBlank
        String cover,
        @NotNull
        int numberPages,
        @NotNull
        @NotBlank
        String readingStatus,
        @NotNull
        @NotBlank
        String bookType) {

}
