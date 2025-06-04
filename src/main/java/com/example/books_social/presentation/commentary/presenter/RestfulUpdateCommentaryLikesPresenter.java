package com.example.books_social.presentation.commentary.presenter;

import com.example.books_social.application.commentary.update.presenter.UpdateCommentaryLikesPresenter;
import com.example.books_social.application.commentary.update.service.UpdateCommentaryLikesService;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class RestfulUpdateCommentaryLikesPresenter implements UpdateCommentaryLikesPresenter {

    private ResponseEntity<?> responseEntity;

    public RestfulUpdateCommentaryLikesPresenter() {}


    @Override
    public void prepareSuccessView(UpdateCommentaryLikesService.ResponseModel response) {
        RestfulUpdateCommentaryLikesPresenter.ViewModel restfulResponse = new RestfulUpdateCommentaryLikesPresenter.ViewModel(
            response.commentaryId(),
            response.likes()
        );
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
        private final Integer likes;

        public ViewModel(UUID commentaryId, Integer likes) {
            this.commentaryId = commentaryId;
            this.likes = likes;
        }
        public UUID getCommentaryId() { return commentaryId; }
        public Integer getLikes() { return likes; }
    }

}
