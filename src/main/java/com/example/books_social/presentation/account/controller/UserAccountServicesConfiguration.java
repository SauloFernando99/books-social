package com.example.books_social.presentation.account.controller;

import com.example.books_social.application.account.create.CreateUserAccountServiceImpl;
import com.example.books_social.application.account.repository.UserAccountRepository;
import com.example.books_social.domain.services.UuidGeneratorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAccountServicesConfiguration {

    @Bean
    public CreateUserAccountServiceImpl createUserAccountService(
        UserAccountRepository repository,
        UuidGeneratorService uuidGeneratorService
    ) {
        return new CreateUserAccountServiceImpl(
            repository,
            uuidGeneratorService
        );
    }
}
