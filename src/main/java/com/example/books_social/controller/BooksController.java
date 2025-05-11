package com.example.books_social.controller;

import com.example.books_social.book.Book;
import com.example.books_social.book.BookData;
import com.example.books_social.book.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BooksController {
    @Autowired
    private BookRepository repository;
    @PostMapping
    @Transactional
    public void register(@RequestBody BookData data){
        repository.save(new Book(data));
    }
}
