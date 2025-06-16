package com.example.books_social.application.commentary.update.service;

import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.update.presenter.UpdateCommentaryPresenter;

import java.util.UUID;

public interface UpdateCommentaryService {
    void updateCommentary(
        UpdateCommentaryPresenter presenter,
        RequestModel request
    );

    public record RequestModel(
       UUID commentaryId,
       String CommentaryText,
       Integer readPages,
       String reaction,
       boolean isSpoiler
    ) {}

    public record ResponseModel(
        UUID commentaryId,
        UUID bookId,
        CommentaryDto commentary
    ) {}
}
