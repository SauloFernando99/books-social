package com.example.books_social.application.book.find.services;

import com.example.books_social.application.book.find.presenter.FindBookPresenter;
import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;

import java.util.List;

public class FindBookServiceImpl implements FindBookService{

    private final BookRepository bookRepository;

    public FindBookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void findBook(FindBookPresenter presenter, FindBookService.RequestModel request) {
        BookDto book = bookRepository.findById(request.bookId());

        if (book == null) {
            String message = "There's no book of id: " + request.bookId() + ".";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }

        FindBookService.ResponseModel response = new FindBookService.ResponseModel(book);

        presenter.prepareSuccessView(response);
    }
}
