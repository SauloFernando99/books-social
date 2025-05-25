package com.example.books_social.presentation.commentary.controller;

import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.commentary.create.CreateCommentaryServiceImpl;
import com.example.books_social.application.commentary.delete.DeleteCommentaryServiceImpl;
import com.example.books_social.application.commentary.find.service.FindAllCommentsServiceImpl;
import com.example.books_social.application.commentary.find.service.FindCommentaryServiceImpl;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.commentary.update.UpdateCommentaryServiceImpl;
import com.example.books_social.domain.services.UuidGeneratorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentaryServicesConfiguration {
    CommentaryRepository commentaryRepository;
    BookRepository bookRepository;
    UuidGeneratorService uuidGeneratorService;

    @Bean
    public CreateCommentaryServiceImpl createCommentaryService(
        CommentaryRepository commentaryRepository,
        BookRepository bookRepository,
        UuidGeneratorService uuidGeneratorService
    ){
        return new CreateCommentaryServiceImpl(
            commentaryRepository,
            bookRepository,
            uuidGeneratorService
        );
    }

    @Bean
    public FindAllCommentsServiceImpl findAllCommentsService(
        CommentaryRepository commentaryRepository,
        BookRepository bookRepository
    ) {
        return new FindAllCommentsServiceImpl(
            commentaryRepository,
            bookRepository
        );
    }

    @Bean
    public FindCommentaryServiceImpl findCommentaryService(
        CommentaryRepository commentaryRepository
    ) {
        return new FindCommentaryServiceImpl(
            commentaryRepository
        );
    }

    @Bean
    public DeleteCommentaryServiceImpl deleteCommentaryService(
        CommentaryRepository commentaryRepository
    ) {
        return new DeleteCommentaryServiceImpl(
            commentaryRepository
        );
    }

    @Bean
    public UpdateCommentaryServiceImpl updateCommentaryService(
        CommentaryRepository commentaryRepository
    ) {
        return new UpdateCommentaryServiceImpl(
          commentaryRepository
        );
    }
}
