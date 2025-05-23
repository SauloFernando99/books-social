package com.example.books_social.application.book.find.services;

import com.example.books_social.application.book.find.presenter.FindAllBooksPresenter;
import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.book.repository.BookRepository;

import java.util.List;

public class FindAllBooksServiceImpl implements FindAllBooksService{

    private final BookRepository bookRepository;

    public FindAllBooksServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void findAllByOwner(FindAllBooksPresenter presenter, RequestModel request) {
        List<BookDto> books = bookRepository.findAllBooksByOwner(request.ownerId());

        Integer numberOfBooks = books.size();
        ResponseModel response = new ResponseModel(request.ownerId(), numberOfBooks, books);

        presenter.prepareSuccessView(response);
    }
}
