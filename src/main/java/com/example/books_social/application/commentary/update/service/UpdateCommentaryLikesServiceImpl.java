package com.example.books_social.application.commentary.update.service;

import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.commentary.update.presenter.UpdateCommentaryLikesPresenter;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

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

        if (request.action().equals("increase")) {
            List<UUID> likesList = original.likesList();
            likesList.add(request.userId());
            CommentaryDto updated = new CommentaryDto(
                original.commentaryId(),
                original.bookId(),
                original.userId(),
                original.commentaryText(),
                original.readPages(),
                original.progress(),
                original.reaction(),
                original.isSpoiler(),
                original.likes() + 1,
                likesList,
                original.createdAt());

            likes = updated.likes();

            repository.saveOrUpdate(updated);
        }

        if (request.action().equals("decrease")) {
            if (original.likes() == 0) {
                throw new IllegalArgumentException("Unable to reduce likes count");
            }
            List<UUID> likesList = original.likesList();
            likesList.remove(request.userId());
            CommentaryDto updated = new CommentaryDto(
                    original.commentaryId(),
                    original.bookId(),
                    original.userId(),
                    original.commentaryText(),
                    original.readPages(),
                    original.progress(),
                    original.reaction(),
                    original.isSpoiler(),
                    original.likes() - 1,
                    likesList,
                    original.createdAt());

            likes = updated.likes();

            repository.saveOrUpdate(updated);
        }

        presenter.prepareSuccessView(
            new ResponseModel(
                original.commentaryId(),
                likes
            )
        );
    }
}
