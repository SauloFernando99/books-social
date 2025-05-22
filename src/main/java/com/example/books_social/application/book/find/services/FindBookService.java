package com.example.books_social.application.book.find.services;

import com.example.books_social.application.book.find.presenter.FindBookPresenter;
import com.example.books_social.application.book.repository.BookDto;

import java.util.UUID;

public interface FindBookService {

    void findBook(FindBookPresenter presenter, RequestModel request);

    public record RequestModel(UUID bookId) {}

    public record ResponseModel(BookDto book) {}
}
