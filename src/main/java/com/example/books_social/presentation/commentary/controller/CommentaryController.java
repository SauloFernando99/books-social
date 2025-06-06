package com.example.books_social.presentation.commentary.controller;

import com.example.books_social.application.commentary.create.CreateCommentaryService;
import com.example.books_social.application.commentary.create.CreateCommentaryServiceImpl;
import com.example.books_social.application.commentary.delete.DeleteCommentaryService;
import com.example.books_social.application.commentary.delete.DeleteCommentaryServiceImpl;
import com.example.books_social.application.commentary.find.service.*;
import com.example.books_social.application.commentary.repository.CommentaryMapper;
import com.example.books_social.application.commentary.update.service.UpdateCommentaryLikesService;
import com.example.books_social.application.commentary.update.service.UpdateCommentaryLikesServiceImpl;
import com.example.books_social.application.commentary.update.service.UpdateCommentaryService;
import com.example.books_social.application.commentary.update.service.UpdateCommentaryServiceImpl;
import com.example.books_social.presentation.commentary.presenter.*;
import com.example.books_social.presentation.commentary.requests.PostRequest;
import com.example.books_social.presentation.commentary.requests.PutRequest;
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
    private final FindAllCommentsRandomlyServiceImpl findAllCommentsRandomlyService;
    private final DeleteCommentaryServiceImpl deleteCommentaryService;
    private final UpdateCommentaryServiceImpl updateCommentaryService;
    private final UpdateCommentaryLikesServiceImpl updateCommentaryLikesService;

    @Autowired
    public CommentaryController(
        CreateCommentaryServiceImpl createCommentaryService,
        FindAllCommentsServiceImpl findAllCommentsService,
        FindCommentaryServiceImpl findCommentaryService,
        FindAllCommentsRandomlyServiceImpl findAllCommentsRandomlyService,
        DeleteCommentaryServiceImpl deleteCommentaryService,
        UpdateCommentaryServiceImpl updateCommentaryService,
        UpdateCommentaryLikesServiceImpl updateCommentaryLikesService
    ) {
        this.createCommentaryService = createCommentaryService;
        this.findAllCommentsService = findAllCommentsService;
        this.findCommentaryService = findCommentaryService;
        this.findAllCommentsRandomlyService = findAllCommentsRandomlyService;
        this.deleteCommentaryService = deleteCommentaryService;
        this.updateCommentaryService = updateCommentaryService;
        this.updateCommentaryLikesService = updateCommentaryLikesService;
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

    @GetMapping("random")
    public ResponseEntity<?> findAllRandomly() {
        RestfulFindAllCommentsRandomlyPresenter presenter = new RestfulFindAllCommentsRandomlyPresenter();

        findAllCommentsRandomlyService.findAllCommentsRandomly(presenter, new FindAllCommentsRandomlyService.RequestModel());

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

    @PutMapping("/update/{commentaryId}")
    public ResponseEntity<?> updateCommentary(@PathVariable UUID commentaryId, @RequestBody PutRequest request) {
        RestfulUpdateCommentaryPresenter presenter = new RestfulUpdateCommentaryPresenter();
        UpdateCommentaryService.RequestModel viewModel = CommentaryMapper.toUpdateRequestModel(
            commentaryId,
            request
        );

        updateCommentaryService.updateCommentary(presenter, viewModel);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PatchMapping("/like/{commentaryId}/{action}/{userId}")
    public ResponseEntity<?> updateLikes(
        @PathVariable UUID commentaryId, @PathVariable String action, @PathVariable UUID userId
    ) {
        RestfulUpdateCommentaryLikesPresenter presenter = new RestfulUpdateCommentaryLikesPresenter();
        UpdateCommentaryLikesService.RequestModel request = new UpdateCommentaryLikesService.RequestModel(
            commentaryId,
            userId,
            action
        );

        updateCommentaryLikesService.updateLikes(presenter, request);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
