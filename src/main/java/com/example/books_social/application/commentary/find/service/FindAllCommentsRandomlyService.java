package com.example.books_social.application.commentary.find.service;

import com.example.books_social.application.commentary.find.presenter.FindAllCommentsRandomlyPresenter;
import com.example.books_social.application.commentary.utils.RandomCommentsResponse;

import java.util.List;
import java.util.UUID;

public interface FindAllCommentsRandomlyService {
    void findAllCommentsRandomly(FindAllCommentsRandomlyPresenter presenter, RequestModel request);

    public record RequestModel(
        UUID userId
    ) {}

    public record ResponseModel(
        List<RandomCommentsResponse> comments
    ) {}
}

