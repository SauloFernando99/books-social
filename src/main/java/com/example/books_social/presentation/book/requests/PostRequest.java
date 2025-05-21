package com.example.books_social.presentation.book.requests;

import java.util.UUID;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PostRequest(
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
        boolean isFavorite
) {}
