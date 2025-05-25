package com.example.books_social.application.commentary.delete;

import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;

public class DeleteCommentaryServiceImpl implements DeleteCommentaryService{
    private final CommentaryRepository repository;

    public DeleteCommentaryServiceImpl(CommentaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteCommentary(DeleteCommentaryPresenter presenter, RequestModel request) {
        boolean exists = repository.existsById(request.commentaryId());

        if (!exists) {
            String message = "Commentary of id: " + request.commentaryId() + "not exists.";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }

        repository.deleteById(request.commentaryId());

        presenter.prepareSuccessView(new ResponseModel(request.commentaryId()));
    }
}
