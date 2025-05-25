package com.example.books_social.application.commentary.find.service;

import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.commentary.find.presenter.FindAllCommentsPresenter;
import com.example.books_social.application.commentary.find.service.FindAllCommentsService;
import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;

import java.util.List;

public class FindAllCommentsServiceImpl implements FindAllCommentsService {
    private final CommentaryRepository repository;
    private final BookRepository bookRepository;

    public FindAllCommentsServiceImpl(
        CommentaryRepository repository,
        BookRepository bookRepository
    ) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void findAllByBookId(FindAllCommentsPresenter presenter, RequestModel request) {
        boolean bookExists = bookRepository.existsById(request.bookId());

        if (!bookExists) {
            String message = "Book of id: " + request.bookId() + "not exists.";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }

        List<CommentaryDto> comments = repository.findAllCommentsByBook(request.bookId());

        presenter.prepareSuccessView(new ResponseModel(
            request.bookId(),
            comments.size(),
            comments
        ));
    }
}
