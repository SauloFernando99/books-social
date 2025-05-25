package com.example.books_social.presentation.commentary.controller;

import com.example.books_social.application.commentary.create.CreateCommentaryService;
import com.example.books_social.application.commentary.create.CreateCommentaryServiceImpl;
import com.example.books_social.application.commentary.delete.DeleteCommentaryService;
import com.example.books_social.application.commentary.delete.DeleteCommentaryServiceImpl;
import com.example.books_social.application.commentary.find.service.FindAllCommentsService;
import com.example.books_social.application.commentary.find.service.FindAllCommentsServiceImpl;
import com.example.books_social.application.commentary.find.service.FindCommentaryService;
import com.example.books_social.application.commentary.find.service.FindCommentaryServiceImpl;
import com.example.books_social.presentation.commentary.presenter.RestfulCreateCommentaryPresenter;
import com.example.books_social.presentation.commentary.presenter.RestfulDeleteCommentaryPresenter;
import com.example.books_social.presentation.commentary.presenter.RestfulFindAllCommentsPresenter;
import com.example.books_social.presentation.commentary.presenter.RestfulFindCommentaryPresenter;
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
    private final FindCommentaryServiceImpl findCommentaryService;

    private final DeleteCommentaryServiceImpl deleteCommentaryService;

    @Autowired
    public CommentaryController(
        CreateCommentaryServiceImpl createCommentaryService,
        FindAllCommentsServiceImpl findAllCommentsService,
        FindCommentaryServiceImpl findCommentaryService,
        DeleteCommentaryServiceImpl deleteCommentaryService
    ) {
        this.createCommentaryService = createCommentaryService;
        this.findAllCommentsService = findAllCommentsService;
        this.findCommentaryService = findCommentaryService;
        this.deleteCommentaryService = deleteCommentaryService;
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

    @GetMapping("/find/{commentaryId}")
    public ResponseEntity<?> findCommentary(@PathVariable UUID commentaryId) {
        RestfulFindCommentaryPresenter presenter = new RestfulFindCommentaryPresenter();

        findCommentaryService.findCommentary(presenter, new FindCommentaryService.RequestModel(commentaryId));

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/delete/{commentaryId}")
    public ResponseEntity<?> deleteCommentary(@PathVariable UUID commentaryId) {
        RestfulDeleteCommentaryPresenter presenter = new RestfulDeleteCommentaryPresenter();

        deleteCommentaryService.deleteCommentary(presenter, new DeleteCommentaryService.RequestModel(commentaryId));

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
