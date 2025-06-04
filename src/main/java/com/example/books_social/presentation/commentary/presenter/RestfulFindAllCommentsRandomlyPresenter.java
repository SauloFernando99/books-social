package com.example.books_social.presentation.commentary.presenter;

import com.example.books_social.application.commentary.find.presenter.FindAllCommentsRandomlyPresenter;
import com.example.books_social.application.commentary.find.service.FindAllCommentsRandomlyService;
import com.example.books_social.application.commentary.utils.RandomCommentsResponse;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RestfulFindAllCommentsRandomlyPresenter implements FindAllCommentsRandomlyPresenter {
    private ResponseEntity<?> responseEntity;

    public RestfulFindAllCommentsRandomlyPresenter(){}

    @Override
    public void prepareSuccessView(FindAllCommentsRandomlyService.ResponseModel response) {
        RestfulFindAllCommentsRandomlyPresenter.ViewModel restfulResponse = new RestfulFindAllCommentsRandomlyPresenter.ViewModel(
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
        private final List<RandomCommentsResponse> comments;

        public ViewModel(List<RandomCommentsResponse> comments) {
            this.comments = comments;
        }

        public List<RandomCommentsResponse> getComments() {
            return comments;
        }
    }
}
