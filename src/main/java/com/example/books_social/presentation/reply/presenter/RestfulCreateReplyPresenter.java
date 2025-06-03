package com.example.books_social.presentation.reply.presenter;

import com.example.books_social.application.commentary.create.CreateCommentaryService;
import com.example.books_social.application.reply.create.CreateReplyPresenter;
import com.example.books_social.application.reply.create.CreateReplyService;
import com.example.books_social.presentation.commentary.presenter.RestfulCreateCommentaryPresenter;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class RestfulCreateReplyPresenter implements CreateReplyPresenter {
    private ResponseEntity<?> responseEntity;

    public RestfulCreateReplyPresenter(){}

    @Override
    public void prepareSuccessView(CreateReplyService.ResponseModel response) {
        RestfulCreateReplyPresenter.ViewModel restfulResponse = new RestfulCreateReplyPresenter.ViewModel(
                response.replyId(),
                response.commentaryId(),
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

    private static class ViewModel extends RepresentationModel<ViewModel> {
        private final UUID replyId;
        private final UUID commentaryId;
        private final LocalDateTime createdAt;

        public ViewModel(UUID replyId, UUID commentaryId, LocalDateTime createdAt) {
            this.replyId = replyId;
            this.commentaryId = commentaryId;
            this.createdAt = createdAt;
        }

        public UUID getReplyId() {
            return replyId;
        }

        public UUID getCommentaryId() {
            return commentaryId;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
    }
}
