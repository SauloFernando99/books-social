package com.example.books_social.presentation.user.controller;

import com.example.books_social.application.reply.find.FindAllRepliesService;
import com.example.books_social.application.user.status.GetUserReadingStatusService;
import com.example.books_social.application.user.status.GetUserReadingStatusServiceImpl;
import com.example.books_social.presentation.reply.presenter.RestfulFindAllRepliesPresenter;
import com.example.books_social.presentation.user.presenter.RestfulGetUserReadingStatusPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/status")
public class UserController {

    private final GetUserReadingStatusServiceImpl getUserReadingStatusService;

    @Autowired
    public UserController(
        GetUserReadingStatusServiceImpl getUserReadingStatusService
    ) {
        this.getUserReadingStatusService = getUserReadingStatusService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUserReadingStatus(@PathVariable UUID userId) {
        RestfulGetUserReadingStatusPresenter presenter = new RestfulGetUserReadingStatusPresenter();

        getUserReadingStatusService.getUserReadingStatus(presenter, new GetUserReadingStatusService.RequestModel(userId));

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
