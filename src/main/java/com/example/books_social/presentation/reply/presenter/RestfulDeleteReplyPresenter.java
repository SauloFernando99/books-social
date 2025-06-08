package com.example.books_social.presentation.reply.presenter;

import com.example.books_social.application.reply.delete.DeleteReplyPresenter;
import com.example.books_social.application.reply.delete.DeleteReplyService;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class RestfulDeleteReplyPresenter implements DeleteReplyPresenter {
    private ResponseEntity<?> responseEntity;
    public RestfulDeleteReplyPresenter() {}

    @Override
    public void prepareSuccessView(DeleteReplyService.ResponseModel response) {
        RestfulDeleteReplyPresenter.ViewModel restfulResponse = new RestfulDeleteReplyPresenter.ViewModel(
            response.replyId()
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

    private static class ViewModel extends RepresentationModel<RestfulDeleteReplyPresenter.ViewModel> {
        private final UUID replyId;

        public ViewModel(UUID replyId) {
            this.replyId = replyId;
        }

        public UUID getReplyId() {
            return replyId;
        }
    }
}
