package com.example.books_social.presentation.commentary.controller;

import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.commentary.create.CreateCommentaryService;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.domain.services.UuidGeneratorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentaryServicesConfiguration {
    CommentaryRepository commentaryRepository;
    BookRepository bookRepository;
    UuidGeneratorService uuidGeneratorService;

    @Bean
    void CreateCommentaryService(
        CommentaryRepository commentaryRepository,
        BookRepository bookRepository,
        UuidGeneratorService uuidGeneratorService
    ){
        this.commentaryRepository = commentaryRepository;
        this.bookRepository = bookRepository;
        this.uuidGeneratorService = uuidGeneratorService;
    }
}
