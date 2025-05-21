package com.example.books_social.application.book.find.services;

import com.example.books_social.application.book.find.presenter.FindAllBooksPresenter;
import com.example.books_social.application.book.repository.BookRepository;

import java.util.UUID;

public class FindAllBooksServiceImpl implements FindAllBooksService{

    private final BookRepository bookRepository;

    public FindAllBooksServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void findAllByOwner(FindAllBooksPresenter presenter, UUID ownerId) {
        bookRepository.findAllBooksByOwner(ownerId);
    }
}
