package com.example.books_social.presentation.book.presenter;

import com.example.books_social.application.book.find.presenter.FindBookPresenter;
import com.example.books_social.application.book.find.services.FindBookService;
import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public class RestfulFindBookPresenter implements FindBookPresenter {

    ResponseEntity<?> responseEntity;

    public RestfulFindBookPresenter() {}

    @Override
    public void prepareSuccessView(FindBookService.ResponseModel response) {
        ViewModel restfulResponse = new ViewModel(response.ownerId(), response.book());

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

    private static class ViewModel extends RepresentationModel<RestfulFindBookPresenter.ViewModel> {
        private final UUID ownerId;
        private final BookDto book;

        private ViewModel(UUID ownerId, BookDto book) {
            this.ownerId = ownerId;
            this.book = book;
        }

        public UUID getOwnerId() {
            return ownerId;
        }

        public BookDto getBook() {
            return book;
        }
    }
}
