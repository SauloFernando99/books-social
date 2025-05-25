package com.example.books_social.application.commentary.delete;

import java.util.UUID;

public interface DeleteCommentaryService {
    void deleteCommentary(
        DeleteCommentaryPresenter presenter,
        RequestModel request
    );

    public record RequestModel(
        UUID commentaryId
    ) {}

    public record ResponseModel(
       UUID commentaryId
    ) {}
}
