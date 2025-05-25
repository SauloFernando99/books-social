package com.example.books_social.application.commentary.update;

import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.repository.CommentaryMapper;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;
import com.example.books_social.domain.model.comentary.Commentary;

public class UpdateCommentaryServiceImpl implements UpdateCommentaryService{
    private final CommentaryRepository repository;

    public UpdateCommentaryServiceImpl(CommentaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateCommentary(UpdateCommentaryPresenter presenter, RequestModel request) {
        CommentaryDto original = repository.findCommentary(request.commentaryId());

        if (original == null) {
            String message = "There is no commentary of id: " + request.commentaryId() + ".";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }

        Commentary updatedCommentary = CommentaryMapper.updateFrom(original, request);
        CommentaryDto updated = CommentaryMapper.toDto(updatedCommentary);

        if (!updated.equals(original)) {
            repository.saveOrUpdate(updated);
        }

        presenter.prepareSuccessView(new ResponseModel(updated.commentaryId(), updated.bookId(), updated));
    }
}
