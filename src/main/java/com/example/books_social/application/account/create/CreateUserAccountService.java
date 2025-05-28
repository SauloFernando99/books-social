package com.example.books_social.application.account.create;

public interface CreateUserAccountService {
    void createUserAccount(
        CreateUserAccountPresenter presenter,
        RequestModel request
    );

    public record RequestModel(
        String username,

    ) {}

    public record ResponseModel(

    ) {}
}
