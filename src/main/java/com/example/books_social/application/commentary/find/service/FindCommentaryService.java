package com.example.books_social.application.commentary.find.service;

import com.example.books_social.application.commentary.find.presenter.FindCommentaryPresenter;
import com.example.books_social.application.commentary.repository.CommentaryDto;

import java.util.UUID;

public interface FindCommentaryService {

    void findCommentary(FindCommentaryPresenter presenter, RequestModel request);

    public record RequestModel(UUID commentaryId) {}

    public record ResponseModel(
        UUID commentaryId,
        UUID bookId,
        CommentaryDto commentary
    ) {}
}
