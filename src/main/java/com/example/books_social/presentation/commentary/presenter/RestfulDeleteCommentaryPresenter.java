package com.example.books_social.presentation.commentary.presenter;

import com.example.books_social.application.commentary.delete.DeleteCommentaryPresenter;
import com.example.books_social.application.commentary.delete.DeleteCommentaryService;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class RestfulDeleteCommentaryPresenter implements DeleteCommentaryPresenter {

    private ResponseEntity<?> responseEntity;

    public RestfulDeleteCommentaryPresenter() {}

    @Override
    public void prepareSuccessView(DeleteCommentaryService.ResponseModel response) {
        RestfulDeleteCommentaryPresenter.ViewModel restfulResponse = new RestfulDeleteCommentaryPresenter.ViewModel(
          response.commentaryId()
        );

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(restfulResponse);
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

    private static class ViewModel extends RepresentationModel<RestfulDeleteCommentaryPresenter.ViewModel> {
        private final UUID commentaryId;

        public ViewModel(UUID commentaryId) {
            this.commentaryId = commentaryId;
        }

        public UUID getCommentaryId() {
            return commentaryId;
        }
    }
}
