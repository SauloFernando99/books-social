package com.example.books_social.application.commentary.update.service;

import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.update.presenter.UpdateCommentaryLikesPresenter;
import com.example.books_social.domain.model.comentary.CommentaryId;

import java.util.UUID;

public interface UpdateCommentaryLikesService {
    void updateLikes(
        UpdateCommentaryLikesPresenter presenter,
        RequestModel request
    );

    public record RequestModel(
        UUID commentaryId,
        String action
    ) {}

    public record ResponseModel(
       UUID commentaryId,
       Integer likes
    ) {}
}
