package com.example.books_social.presentation.user.controller;

import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.user.status.GetUserReadingStatusServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServicesConfiguration {
    BookRepository bookRepository;

    @Bean
    public GetUserReadingStatusServiceImpl getUserReadingStatusService(
        BookRepository bookRepository
    ) {
        return new GetUserReadingStatusServiceImpl(
            bookRepository
        );
    }
}
