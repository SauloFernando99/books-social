package com.example.books_social.application.commentary.create;

import com.example.books_social.application.book.create.CreateBookPresenter;
import com.example.books_social.domain.model.book.Book;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CreateCommentaryService {
    void createCommentary(
        CreateCommentaryPresenter presenter,
        RequestModel request
    );

    public record RequestModel(
       UUID bookId,
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
