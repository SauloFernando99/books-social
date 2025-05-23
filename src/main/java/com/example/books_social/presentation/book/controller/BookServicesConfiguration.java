package com.example.books_social.presentation.book.controller;

import com.example.books_social.application.book.create.CreateBookServiceImpl;
import com.example.books_social.application.book.delete.DeleteBookServiceImpl;
import com.example.books_social.application.book.find.services.FindAllBooksServiceImpl;
import com.example.books_social.application.book.find.services.FindBookServiceImpl;
import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.book.update.service.UpdateBookServiceImpl;
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

    @Bean
    public FindBookServiceImpl findBookService(
        BookRepository bookRepository
    ) {
        return new FindBookServiceImpl(bookRepository);
    }

    @Bean
    public UpdateBookServiceImpl updateBookService(
       BookRepository bookRepository
    ) {
        return new UpdateBookServiceImpl(bookRepository);
    }

    @Bean
    public DeleteBookServiceImpl deleteBookService(
        BookRepository bookRepository
    ) {
        return new DeleteBookServiceImpl(bookRepository);
    }
}
