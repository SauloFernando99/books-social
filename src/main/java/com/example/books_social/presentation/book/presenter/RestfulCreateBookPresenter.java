package com.example.books_social.presentation.book.presenter;

import com.example.books_social.application.book.create.CreateBookPresenter;
import com.example.books_social.application.book.create.CreateBookService;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RestfulCreateBookPresenter implements CreateBookPresenter {

    private ResponseEntity<?> responseEntity;

    public RestfulCreateBookPresenter(){}

    @Override
    public void prepareSuccessView(CreateBookService.ResponseModel response) {
        ViewModel restfulResponse = new ViewModel(
              response.bookId(),
              response.title(),
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
        private final UUID bookId;
        private final String title;
        private final LocalDateTime createdAt;

        public ViewModel(UUID bookId, String title, LocalDateTime createdAt) {
            this.bookId = bookId;
            this.title = title;
            this.createdAt = createdAt;
        }

        public UUID getBookId() {
            return bookId;
        }

        public String getTitle() {
            return title;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
    }
}
