package com.example.books_social.presentation.book.presenter;

import com.example.books_social.application.book.update.presenter.UpdateBookPresenter;
import com.example.books_social.application.book.update.service.UpdateBookService;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class RestfulUpdateBookPresenter implements UpdateBookPresenter {
    private ResponseEntity<?> responseEntity;
    public RestfulUpdateBookPresenter(){}
    @Override
    public void prepareSuccessView(UpdateBookService.ResponseModel response) {
        RestfulUpdateBookPresenter.ViewModel restfulResponse = new RestfulUpdateBookPresenter.ViewModel(
            response.ownerId(),
            response.bookId()
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
        private final UUID ownerId;
        private final UUID bookId;

        public ViewModel(UUID ownerId, UUID bookId) {
            this.ownerId = ownerId;
            this.bookId = bookId;
        }
        public UUID getOwnerId() { return ownerId; }
        public UUID getBookId() {
            return bookId;
        }
    }

}
