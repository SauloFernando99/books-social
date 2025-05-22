package com.example.books_social.application.book.find.services;

import com.example.books_social.application.book.find.presenter.FindAllBooksPresenter;
import com.example.books_social.application.book.repository.BookDto;

import java.util.List;
import java.util.UUID;

public interface FindAllBooksService {

    void findAllByOwner(FindAllBooksPresenter presenter, RequestModel request);

    public record RequestModel(UUID ownerId) {}

    public record ResponseModel(UUID ownerId, List<BookDto> books) {}

}
