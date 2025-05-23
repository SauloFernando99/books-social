package com.example.books_social.application.book.delete;

import java.util.UUID;

public interface DeleteBookService {

    void deleteBook(
        DeleteBookPresenter presenter,
        RequestModel request
    );

    public record RequestModel(
        UUID bookId
    ) {}

    public record ResponseModel(
        UUID bookId
    ) {}
}
