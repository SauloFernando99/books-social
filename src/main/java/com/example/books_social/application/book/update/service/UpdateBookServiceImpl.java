package com.example.books_social.application.book.update.service;

import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.book.repository.BookMapper;
import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.book.update.presenter.UpdateBookPresenter;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;
import com.example.books_social.domain.model.book.Book;

public class UpdateBookServiceImpl implements UpdateBookService{
    private final BookRepository bookRepository;

    public UpdateBookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void updateBook(UpdateBookPresenter presenter, RequestModel request) {
        BookDto original = bookRepository.findById(request.bookId());

        if (original == null) {
            String message = "There is no book of id: " + request.bookId() + ".";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }

        BookDto updated = BookMapper.updateFrom(original, request);

        if (!updated.equals(original)) {
            bookRepository.saveOrUpdate(updated);
        }

        presenter.prepareSuccessView(new ResponseModel(updated.ownerId(), updated.bookId()));
    }
}
