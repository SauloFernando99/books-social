package com.example.books_social.application.user.statistics;

import com.example.books_social.application.book.repository.BookDto;

import java.util.UUID;

public interface GetUserReadingStatisticsService {
    void getUserReadingStatistics(
        GetUserReadingStatisticsPresenter presenter,
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
