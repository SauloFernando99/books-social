package com.example.books_social.presentation.reply.controller;

import com.example.books_social.application.reply.create.CreateReplyService;
import com.example.books_social.application.reply.create.CreateReplyServiceImpl;
import com.example.books_social.application.reply.find.FindAllRepliesService;
import com.example.books_social.application.reply.find.FindAllRepliesServiceImpl;
import com.example.books_social.presentation.reply.presenter.RestfulCreateReplyPresenter;
import com.example.books_social.presentation.reply.presenter.RestfulFindAllRepliesPresenter;
import com.example.books_social.presentation.reply.requests.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/reply")
public class ReplyController {
    private final CreateReplyServiceImpl createReplyService;
    private final FindAllRepliesServiceImpl findAllRepliesService;

    @Autowired
    public ReplyController(
        CreateReplyServiceImpl createReplyService,
        FindAllRepliesServiceImpl findAllRepliesService
    ) {
        this.createReplyService = createReplyService;
        this.findAllRepliesService = findAllRepliesService;
    }

    @PostMapping
    public ResponseEntity<?> createReply(@RequestBody PostRequest request) {
        RestfulCreateReplyPresenter presenter = new RestfulCreateReplyPresenter();

        CreateReplyService.RequestModel model = new CreateReplyService.RequestModel(
                request.userId(),
                request.commentaryId(),
                request.replyText()
        );

        createReplyService.createReply(presenter, model);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/{commentaryId}")
    public ResponseEntity<?> findAllByCommentaryId(@PathVariable UUID commentaryId) {
        RestfulFindAllRepliesPresenter presenter = new RestfulFindAllRepliesPresenter();

        findAllRepliesService.findAllByCommentaryId(presenter, new FindAllRepliesService.RequestModel(commentaryId));

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
