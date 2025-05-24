package com.example.books_social.presentation.commentary.presenter;

import com.example.books_social.application.commentary.create.CreateCommentaryPresenter;
import com.example.books_social.application.commentary.create.CreateCommentaryService;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class RestfulCreateCommentaryPresenter implements CreateCommentaryPresenter {
    private ResponseEntity<?> responseEntity;

    public RestfulCreateCommentaryPresenter(){}

    @Override
    public void prepareSuccessView(CreateCommentaryService.ResponseModel response) {
        RestfulCreateCommentaryPresenter.ViewModel restfulResponse = new RestfulCreateCommentaryPresenter.ViewModel(
                response.commentaryId(),
                response.userId(),
                response.createdAt()
        );

        responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(restfulResponse);
    }

    @Override
    public void prepareFailView(Throwable throwable) {
        responseEntity = ErrorResponseEntityFactory.createErrorResponseFrom(throwable);
    }

    @Override
    public boolean isDone() {
        return responseEntity != null;
    }

    public ResponseEntity<?> getResponseEntity() {
        return responseEntity;
    }

    private static class ViewModel extends RepresentationModel<RestfulCreateCommentaryPresenter.ViewModel> {
        private final UUID commentaryId;
        private final UUID userId;
        private final LocalDateTime createdAt;

        public ViewModel(UUID commentaryId, UUID userId, LocalDateTime createdAt) {
            this.commentaryId = commentaryId;
            this.userId = userId;
            this.createdAt = createdAt;
        }

        public UUID getCommentaryId() {
            return commentaryId;
        }

        public UUID getUserId() {
            return userId;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
    }
}
