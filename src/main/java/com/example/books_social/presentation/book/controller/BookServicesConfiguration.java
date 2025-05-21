package com.example.books_social.presentation.book.controller;

import com.example.books_social.application.book.create.CreateBookServiceImpl;
import com.example.books_social.application.book.find.services.FindAllBooksServiceImpl;
import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.domain.services.UuidGeneratorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookServicesConfiguration {

    @Bean
    public CreateBookServiceImpl createBookService(
        BookRepository bookRepository,
        UuidGeneratorService uuidGeneratorService
    ) {
        return new CreateBookServiceImpl(
            bookRepository,
            uuidGeneratorService
        );
    }

    @Bean
    public FindAllBooksServiceImpl findAllBooksService(
        BookRepository bookRepository
    ) {
        return new FindAllBooksServiceImpl(bookRepository);
    }
}
