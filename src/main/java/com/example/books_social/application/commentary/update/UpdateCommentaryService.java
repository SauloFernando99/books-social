package com.example.books_social.application.commentary.update;

import com.example.books_social.application.commentary.repository.CommentaryDto;

import java.util.UUID;

public interface UpdateCommentaryService {
    void updateCommentary(
        UpdateCommentaryPresenter presenter,
        RequestModel request
    );

    public record RequestModel(
       UUID commentaryId,
       String CommentaryText,
       Integer progress,
       String reaction
    ) {}

    public record ResponseModel(
        UUID commentaryId,
        UUID bookId,
        CommentaryDto commentary
    ) {}
}
