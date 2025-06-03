package com.example.books_social.presentation.reply.presenter;

import com.example.books_social.application.commentary.find.service.FindAllCommentsService;
import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.reply.find.FindAllRepliesPresenter;
import com.example.books_social.application.reply.find.FindAllRepliesService;
import com.example.books_social.application.reply.repository.ReplyDto;
import com.example.books_social.presentation.commentary.presenter.RestfulFindAllCommentsPresenter;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public class RestfulFindAllRepliesPresenter implements FindAllRepliesPresenter {
    private ResponseEntity<?> responseEntity;

    public  RestfulFindAllRepliesPresenter(){}

    @Override
    public void prepareSuccessView(FindAllRepliesService.ResponseModel response) {
        RestfulFindAllRepliesPresenter.ViewModel restfulResponse = new RestfulFindAllRepliesPresenter.ViewModel(
                response.commentaryId(),
                response.numberOfReplies(),
                response.replies()
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

    private static class ViewModel extends RepresentationModel<RestfulFindAllRepliesPresenter.ViewModel> {
        private final UUID commentaryId;
        private final Integer numberOfReplies;
        private final List<ReplyDto> replies;

        public ViewModel(UUID commentaryId, Integer numberOfReplies, List<ReplyDto> replies) {
            this.commentaryId = commentaryId;
            this.numberOfReplies = numberOfReplies;
            this.replies = replies;
        }

        public UUID getCommentaryId() {
            return commentaryId;
        }

        public Integer getNumberOfReplies() {
            return numberOfReplies;
        }

        public List<ReplyDto> getReplies() {
            return replies;
        }
    }
}
