package com.example.books_social.presentation.book.presenter;

import com.example.books_social.application.book.delete.DeleteBookPresenter;
import com.example.books_social.application.book.delete.DeleteBookService;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class RestfulDeleteBookPresenter implements DeleteBookPresenter {

    private ResponseEntity<?> responseEntity;
    public RestfulDeleteBookPresenter(){}

    @Override
    public void prepareSuccessView(DeleteBookService.ResponseModel response) {
        RestfulDeleteBookPresenter.ViewModel restfulResponse = new RestfulDeleteBookPresenter.ViewModel(
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

    private static class ViewModel extends RepresentationModel<RestfulDeleteBookPresenter.ViewModel> {
        private final UUID bookId;

        public ViewModel(UUID bookId) {
            this.bookId = bookId;
        }

        public UUID getBookId() {
            return bookId;
        }
    }
}
