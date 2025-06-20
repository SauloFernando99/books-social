package com.example.books_social.application.user.statistics;

import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.book.repository.BookRepository;

public class GetUserReadingStatisticsServiceImpl implements GetUserReadingStatisticsService {
    private final BookRepository bookRepository;

    public GetUserReadingStatisticsServiceImpl(
        BookRepository bookRepository
    ) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void getUserReadingStatistics(GetUserReadingStatisticsPresenter presenter, RequestModel request) {
        BookDto longestReadBook = bookRepository.findLongestReadBook(request.userId());
        BookDto shortestReadBook = bookRepository.findShortestReadBook(request.userId());
        Integer totalRead = bookRepository.findTotalRead(request.userId());
        Integer readPages = bookRepository.findReadPages(request.userId());
        String mostReadGenre = bookRepository.findMostReadGenre(request.userId());

        presenter.prepareSuccessView(
            new ResponseModel(
                longestReadBook,
                shortestReadBook,
                totalRead,
                readPages,
                mostReadGenre
            )
        );
    }
}
