package com.example.books_social.presentation.commentary.controller;

import com.example.books_social.application.commentary.create.CreateCommentaryService;
import com.example.books_social.application.commentary.create.CreateCommentaryServiceImpl;
import com.example.books_social.application.commentary.find.service.FindAllCommentsService;
import com.example.books_social.application.commentary.find.service.FindAllCommentsServiceImpl;
import com.example.books_social.presentation.commentary.presenter.RestfulCreateCommentaryPresenter;
import com.example.books_social.presentation.commentary.presenter.RestfulFindAllCommentsPresenter;
import com.example.books_social.presentation.commentary.requests.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/commentary")
public class CommentaryController {
    private final CreateCommentaryServiceImpl createCommentaryService;
    private final FindAllCommentsServiceImpl findAllCommentsService;

    @Autowired
    public CommentaryController(
        CreateCommentaryServiceImpl createCommentaryService,
        FindAllCommentsServiceImpl findAllCommentsService
    ) {
        this.createCommentaryService = createCommentaryService;
        this.findAllCommentsService = findAllCommentsService;
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

    @GetMapping("/{bookId}")
    public ResponseEntity<?> findAllByBook(@PathVariable UUID bookId) {
        RestfulFindAllCommentsPresenter presenter = new RestfulFindAllCommentsPresenter();

        findAllCommentsService.findAllByBookId(presenter, new FindAllCommentsService.RequestModel(bookId));

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
