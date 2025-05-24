package com.example.books_social.application.commentary.create;

import com.example.books_social.application.book.create.CreateBookPresenter;
import com.example.books_social.domain.model.book.Book;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CreateCommentaryService {
    void createCommentary(
        CreateBookPresenter presenter,
        RequestModel request
    );

    public record RequestModel(
       UUID BookId,
       UUID userId,
       String commentaryText,
       Integer progress,
       String reaction
    ) {}

    public record ResponseModel(
        UUID commentaryId,
        UUID userId,
        LocalDateTime createdAt
    ) {}
}
