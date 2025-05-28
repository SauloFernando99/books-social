package com.example.books_social.presentation.account.controller;

import com.example.books_social.application.account.create.CreateUserAccountService;
import com.example.books_social.application.account.create.CreateUserAccountServiceImpl;
import com.example.books_social.presentation.account.presenter.RestfulCreateUserAccountPresenter;
import com.example.books_social.presentation.account.requests.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final CreateUserAccountServiceImpl createUserAccountService;

    @Autowired
    public UserController(
        CreateUserAccountServiceImpl createUserAccountService
    ) {
        this.createUserAccountService = createUserAccountService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody PostRequest request) {
        RestfulCreateUserAccountPresenter presenter = new RestfulCreateUserAccountPresenter();

        CreateUserAccountService.RequestModel model = new CreateUserAccountService.RequestModel(
            request.username(),
            request.email(),
            request.password(),
            request.userPhoto()
        );

        createUserAccountService.createUserAccount(presenter, model);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
