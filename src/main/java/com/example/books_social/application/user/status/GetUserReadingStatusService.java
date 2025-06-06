package com.example.books_social.application.user.status;

import com.example.books_social.application.book.repository.BookDto;

import java.util.UUID;

public interface GetUserReadingStatusService {
    void getUserReadingStatus (
        GetUserReadingStatusPresenter presenter,
        RequestModel request
    );


    public record RequestModel(
        UUID userId
    ) {}

    public record ResponseModel(
        BookDto longestReadBook,
        BookDto shortestReadBook,
        Integer totalRead,
        Integer readPages,
        String mostReadGenre
    ) {}
}
