package com.example.books_social.application.book.update.service;

import com.example.books_social.application.book.update.presenter.UpdateBookPresenter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface UpdateBookService {
    void updateBook(
        UpdateBookPresenter presenter,
        RequestModel request
    );

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestModel (
            UUID bookId,
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
            Boolean isFavorite
    ) {}

    public record ResponseModel(
            UUID ownerId,
            UUID bookId
    ) {}

}
