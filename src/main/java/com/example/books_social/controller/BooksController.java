package com.example.books_social.controller;

import com.example.books_social.book.Book;
import com.example.books_social.book.BookData;
import com.example.books_social.book.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BooksController {
    @Autowired
    private BookRepository repository;
    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid BookData data){
        repository.save(new Book(data));
    }

    @GetMapping
    public List<Book> list() {
        return repository.findAll();
    }
}
