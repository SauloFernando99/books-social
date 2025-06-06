package com.example.books_social.application.user.status;

import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.book.repository.BookRepository;

public class GetUserReadingStatusServiceImpl implements GetUserReadingStatusService{
    private final BookRepository bookRepository;

    public GetUserReadingStatusServiceImpl(
        BookRepository bookRepository
    ) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void getUserReadingStatus(GetUserReadingStatusPresenter presenter, RequestModel request) {
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
