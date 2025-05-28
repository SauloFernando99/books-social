package com.example.books_social.presentation.account.presenter;

import com.example.books_social.application.account.create.CreateUserAccountPresenter;
import com.example.books_social.application.account.create.CreateUserAccountService;
import com.example.books_social.presentation.shared.error.ErrorResponseEntityFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RestfulCreateUserAccountPresenter implements CreateUserAccountPresenter {
    private ResponseEntity<?> responseEntity;

    public RestfulCreateUserAccountPresenter(){}

    @Override
    public void prepareSuccessView(CreateUserAccountService.ResponseModel response) {
        RestfulCreateUserAccountPresenter.ViewModel restfulResponse = new RestfulCreateUserAccountPresenter.ViewModel(
            response.userId(),
            response.username(),
            response.userEmail(),
            response.userPhoto()
        );

        responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(restfulResponse);
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

    private static class ViewModel extends RepresentationModel<RestfulCreateUserAccountPresenter.ViewModel> {
        private final UUID userAccountId;
        private final String username;
        private final String email;
        private final String userPhoto;

        public ViewModel(UUID userAccountId, String username, String email, String userPhoto) {
            this.userAccountId = userAccountId;
            this.username = username;
            this.email = email;
            this.userPhoto = userPhoto;
        }

        public UUID getUserAccountId() {
            return userAccountId;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getUserPhoto() {
            return userPhoto;
        }
    }
}
