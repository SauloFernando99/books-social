package com.example.books_social.application.commentary.update.service;

import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.commentary.update.presenter.UpdateCommentaryLikesPresenter;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;
//TODO needs to be refactored to remove validation logic from the service and put it into the entity
public class UpdateCommentaryLikesServiceImpl implements UpdateCommentaryLikesService{
    private final CommentaryRepository repository;

    public UpdateCommentaryLikesServiceImpl(
        CommentaryRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public void updateLikes(UpdateCommentaryLikesPresenter presenter, RequestModel request) {
        CommentaryDto original = repository.findCommentary(request.commentaryId());

        if (original == null) {
            String message = "There is no commentary of id: " + request.commentaryId() + ".";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }
        
        Integer likes = null;

        if (request.action().equals("Increase")) {
            CommentaryDto updated = new CommentaryDto(
                original.commentaryId(),
                original.bookId(),
                original.userId(),
                original.commentaryText(),
                original.progress(),
                original.reaction(),
                original.likes() + 1,
                original.createdAt());
            
            likes = updated.likes();
        }

        if (request.action().equals("Reduce")) {
            if (original.likes() == 0) {
                throw new IllegalArgumentException("Unable to reduce likes count");
            }
            CommentaryDto updated = new CommentaryDto(
                    original.commentaryId(),
                    original.bookId(),
                    original.userId(),
                    original.commentaryText(),
                    original.progress(),
                    original.reaction(),
                    original.likes() - 1,
                    original.createdAt());

            likes = updated.likes();
        }
        
        presenter.prepareSuccessView(
            new ResponseModel(
                original.commentaryId(),
                likes
            )
        );
    }
}
