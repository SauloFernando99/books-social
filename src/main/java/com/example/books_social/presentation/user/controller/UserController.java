package com.example.books_social.presentation.user.controller;

import com.example.books_social.application.user.statistics.GetUserReadingStatisticsService;
import com.example.books_social.application.user.statistics.GetUserReadingStatisticsServiceImpl;
import com.example.books_social.presentation.user.presenter.RestfulGetUserReadingStatisticsPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/statistics")
public class UserController {

    private final GetUserReadingStatisticsServiceImpl getUserReadingStatusService;

    @Autowired
    public UserController(
        GetUserReadingStatisticsServiceImpl getUserReadingStatusService
    ) {
        this.getUserReadingStatusService = getUserReadingStatusService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUserReadingStatus(@PathVariable UUID userId) {
        RestfulGetUserReadingStatisticsPresenter presenter = new RestfulGetUserReadingStatisticsPresenter();

        getUserReadingStatusService.getUserReadingStatistics(presenter, new GetUserReadingStatisticsService.RequestModel(userId));

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
