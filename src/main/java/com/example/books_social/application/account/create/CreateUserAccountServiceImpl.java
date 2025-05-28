package com.example.books_social.application.account.create;

import com.example.books_social.application.account.repository.UserAccountMapper;
import com.example.books_social.application.account.repository.UserAccountRepository;
import com.example.books_social.application.shared.exceptions.UniquenessViolationException;
import com.example.books_social.domain.model.account.UserAccount;
import com.example.books_social.domain.model.account.UserAccountId;
import com.example.books_social.domain.services.UuidGeneratorService;

public class CreateUserAccountServiceImpl implements CreateUserAccountService{
    private final UserAccountRepository repository;
    private final UuidGeneratorService uuidGeneratorService;

    public CreateUserAccountServiceImpl(
        UserAccountRepository repository,
        UuidGeneratorService uuidGeneratorService
    ) {
        this.repository = repository;
        this.uuidGeneratorService = uuidGeneratorService;
    }

    @Override
    public void createUserAccount(CreateUserAccountPresenter presenter, RequestModel request) {
        boolean exists = repository.existsByEmail(request.email());

        if (exists) {
            String message = "This email is already in use.";
            presenter.prepareFailView(new UniquenessViolationException(message));
            return;
        }
        UserAccountId userAccountId = new UserAccountId(uuidGeneratorService.next());
        UserAccount userAccount = UserAccountMapper.fromRequestModel(userAccountId, request);

        repository.create(UserAccountMapper.toDto(userAccount));

        presenter.prepareSuccessView(new CreateUserAccountService.ResponseModel(
            userAccountId.value(),
            request.username(),
            request.email(),
            request.userPhoto()
        ));
    }
}
