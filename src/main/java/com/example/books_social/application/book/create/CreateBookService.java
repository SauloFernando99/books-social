package com.example.books_social.application.book.create;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CreateBookService {
    void createBook(
        CreateBookPresenter createBookPresenter,
        RequestModel request
    );

    public record RequestModel (
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

    public record ResponseModel(
        UUID bookId,
        String title,
        LocalDateTime createdAt
    ) {}
}
