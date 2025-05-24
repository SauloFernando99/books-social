package com.example.books_social.presentation.commentary.presenter;

import com.example.books_social.application.commentary.find.FindAllCommentsPresenter;
import com.example.books_social.application.commentary.find.FindAllCommentsService;
import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RestfulFindAllCommentsPresenter implements FindAllCommentsPresenter {
    private ResponseEntity<?> responseEntity;

    public RestfulFindAllCommentsPresenter(){}

    @Override
    public void prepareSuccessView(FindAllCommentsService.ResponseModel response) {
        RestfulFindAllCommentsPresenter.ViewModel restfulResponse = new RestfulFindAllCommentsPresenter.ViewModel(
            response.bookId(),
            response.numberOfComments(),
            response.comments()
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
        private final UUID bookId;
        private final Integer numberOfComments;
        private final List<CommentaryDto> comments;

        public ViewModel(UUID bookId, Integer numberOfComments, List<CommentaryDto> comments) {
            this.bookId = bookId;
            this.numberOfComments = numberOfComments;
            this.comments = comments;
        }

        public UUID getBookId() {
            return bookId;
        }

        public Integer getNumberOfComments() {
            return numberOfComments;
        }

        public List<CommentaryDto> getComments() {
            return comments;
        }
    }
}
