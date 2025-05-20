package com.example.books_social.application.book.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record BookDto(
        UUID bookId,
        @NotNull UUID ownerId,
        @NotNull @NotBlank String title,
        @NotNull @NotBlank String author,
        @NotNull @NotBlank String genre,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull @NotBlank String review,
        @NotNull @NotBlank String favoriteCharacter,
        @NotNull Integer rating,
        @NotNull @NotBlank String coverUrl,
        @NotNull Integer numberPages,
        @NotNull @NotBlank String readingStatus,
        @NotNull List<@NotBlank String> bookTypes,
        boolean isFavorite
) {}
