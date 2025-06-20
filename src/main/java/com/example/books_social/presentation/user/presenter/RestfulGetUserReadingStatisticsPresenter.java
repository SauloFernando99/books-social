package com.example.books_social.presentation.user.presenter;

import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.user.statistics.GetUserReadingStatisticsPresenter;
import com.example.books_social.application.user.statistics.GetUserReadingStatisticsService;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestfulGetUserReadingStatisticsPresenter implements GetUserReadingStatisticsPresenter {
    private ResponseEntity<?> responseEntity;

    public RestfulGetUserReadingStatisticsPresenter(){}

    @Override
    public void prepareSuccessView(GetUserReadingStatisticsService.ResponseModel response) {
        RestfulGetUserReadingStatisticsPresenter.ViewModel restfulResponse =
            new RestfulGetUserReadingStatisticsPresenter.ViewModel(
                response.longestReadBook(),
                response.shortestReadBook(),
                response.totalRead(),
                response.readPages(),
                response.mostReadGenre()
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
        private final BookDto longestReadBook;
        private final BookDto shortestReadBook;
        private final Integer totalRead;
        private final Integer readPages;
        private final String mostReadGenre;

        public ViewModel(
            BookDto longestReadBook,
            BookDto shortestReadBook,
            Integer totalRead,
            Integer readPages,
            String mostReadGenre
        ) {
            this.longestReadBook = longestReadBook;
            this.shortestReadBook = shortestReadBook;
            this.totalRead = totalRead;
            this.readPages = readPages;
            this.mostReadGenre = mostReadGenre;
        }

        public BookDto getLongestReadBook() {
            return longestReadBook;
        }

        public BookDto getShortestReadBook() {
            return shortestReadBook;
        }

        public Integer getTotalRead() {
            return totalRead;
        }

        public Integer getReadPages() {
            return readPages;
        }

        public String getMostReadGenre() {
            return mostReadGenre;
        }
    }
}
