package com.example.books_social.application.commentary.find.service;

import com.example.books_social.application.commentary.find.presenter.FindCommentaryPresenter;
import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;
import com.example.books_social.domain.model.comentary.Commentary;

public class FindCommentaryServiceImpl implements FindCommentaryService{
    private final CommentaryRepository repository;

    public FindCommentaryServiceImpl(CommentaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void findCommentary(FindCommentaryPresenter presenter, RequestModel request) {
        CommentaryDto dto = repository.findCommentary(request.commentaryId());

        if (dto == null) {
            String message = "Commentary of id: " + request.commentaryId() + "not exists.";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }

        presenter.prepareSuccessView(new ResponseModel(request.commentaryId(), dto.bookId(), dto));
    }
}
