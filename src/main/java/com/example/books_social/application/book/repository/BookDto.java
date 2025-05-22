package com.example.books_social.application.book.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record BookDto(
        UUID bookId,
        UUID ownerId,
        String title,
        String author,
        String genre,
        LocalDate startDate,
        LocalDate endDate,
        String review,
        String favoriteCharacter,
        Integer rating,
        String coverUrl,
        Integer numberPages,
        String readingStatus,
        List<String> bookTypes,
        boolean isFavorite,
        LocalDateTime createdAt
) {}
