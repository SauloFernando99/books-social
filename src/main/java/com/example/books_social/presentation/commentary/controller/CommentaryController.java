package com.example.books_social.presentation.commentary.controller;

import com.example.books_social.application.commentary.create.CreateCommentaryService;
import com.example.books_social.application.commentary.create.CreateCommentaryServiceImpl;
import com.example.books_social.presentation.commentary.presenter.RestfulCreateCommentaryPresenter;
import com.example.books_social.presentation.commentary.requests.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/commentary")
public class CommentaryController {
    private final CreateCommentaryServiceImpl createCommentaryService;

    @Autowired
    public CommentaryController(CreateCommentaryServiceImpl createCommentaryService) {
        this.createCommentaryService = createCommentaryService;
    }

    @PostMapping
    public ResponseEntity<?> createCommentary(@RequestBody PostRequest request) {
        RestfulCreateCommentaryPresenter presenter = new RestfulCreateCommentaryPresenter();

        CreateCommentaryService.RequestModel model = new CreateCommentaryService.RequestModel(
            request.bookId(),
            request.userId(),
            request.commentaryText(),
            request.progress(),
            request.reaction()
        );

        createCommentaryService.createCommentary(presenter, model);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
