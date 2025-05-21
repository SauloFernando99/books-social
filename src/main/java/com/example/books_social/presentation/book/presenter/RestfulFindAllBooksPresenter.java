package com.example.books_social.presentation.book.presenter;

import com.example.books_social.application.book.find.presenter.FindAllBooksPresenter;
import com.example.books_social.application.book.find.services.FindAllBooksService;
import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public class RestfulFindAllBooksPresenter implements FindAllBooksPresenter {

    ResponseEntity<?> responseEntity;
    public RestfulFindAllBooksPresenter() {}


    @Override
    public void prepareSuccessView(FindAllBooksService.ResponseModel response) {
        ViewModel restfulResponse = new ViewModel(response.ownerId(), response.books());

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
        private final List<BookDto> books;

        private ViewModel(UUID ownerId, List<BookDto> books) {
            this.ownerId = ownerId;
            this.books = books;
        }
    }
}
