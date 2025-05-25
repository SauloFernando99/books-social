package com.example.books_social.presentation.commentary.presenter;

import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.update.UpdateCommentaryPresenter;
import com.example.books_social.application.commentary.update.UpdateCommentaryService;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class RestfulUpdateCommentaryPresenter implements UpdateCommentaryPresenter {
    private ResponseEntity<?> responseEntity;
    public RestfulUpdateCommentaryPresenter(){}
    @Override
    public void prepareSuccessView(UpdateCommentaryService.ResponseModel response) {
        RestfulUpdateCommentaryPresenter.ViewModel restfulResponse = new RestfulUpdateCommentaryPresenter.ViewModel(
            response.commentaryId(),
            response.bookId(),
            response.commentary()
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

    private static class ViewModel extends RepresentationModel<ViewModel> {
        private final UUID commentaryId;
        private final UUID bookId;
        private final CommentaryDto commentary;

        public ViewModel(UUID commentaryId, UUID bookId, CommentaryDto commentary) {
            this.commentaryId = commentaryId;
            this.bookId = bookId;
            this.commentary = commentary;
        }
        public UUID getCommentaryId() { return commentaryId; }
        public UUID getBookId() {
            return bookId;
        }
        public CommentaryDto getCommentary() { return commentary; }
    }

}
