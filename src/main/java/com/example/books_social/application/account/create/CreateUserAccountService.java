package com.example.books_social.application.account.create;

import java.util.UUID;

public interface CreateUserAccountService {
    void createUserAccount(
        CreateUserAccountPresenter presenter,
        RequestModel request
    );

    public record RequestModel(
        String username,
        String email,
        String password,
        String userPhoto
    ) {}

    public record ResponseModel(
        UUID userId,
        String username,
        String userEmail,
        String userPhoto
    ) {}
}
