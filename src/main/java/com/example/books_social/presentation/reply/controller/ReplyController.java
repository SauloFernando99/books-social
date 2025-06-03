package com.example.books_social.presentation.reply.controller;

import com.example.books_social.application.reply.create.CreateReplyService;
import com.example.books_social.application.reply.create.CreateReplyServiceImpl;
import com.example.books_social.presentation.reply.presenter.RestfulCreateReplyPresenter;
import com.example.books_social.presentation.reply.requests.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/reply")
public class ReplyController {
    private final CreateReplyServiceImpl createReplyService;

    @Autowired
    public ReplyController(CreateReplyServiceImpl createReplyService) {
        this.createReplyService = createReplyService;
    }

    @PostMapping
    public ResponseEntity<?> createReply(@RequestBody PostRequest request) {
        RestfulCreateReplyPresenter presenter = new RestfulCreateReplyPresenter();

        CreateReplyService.RequestModel model = new CreateReplyService.RequestModel(
                request.commentaryId(),
                request.userId(),
                request.replyText()
        );

        createReplyService.createReply(presenter, model);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
