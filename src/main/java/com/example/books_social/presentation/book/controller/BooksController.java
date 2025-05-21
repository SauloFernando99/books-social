package com.example.books_social.presentation.book.controller;

import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.book.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/book")
public class BooksController {
    @Autowired
    private BookRepository repository;

    @PostMapping
    public void register(@RequestBody @Valid BookDto data){
        repository.create(data);
    }

//    @GetMapping
//    public List<BookDocument> list() {
//        return repository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BookDocument> getBookById(@PathVariable UUID id) {
//        Optional<BookDocument> bookOptional = repository.findById(id);
//
//        if (bookOptional.isPresent()) {
//            BookDocument book = bookOptional.get();
//            return ResponseEntity.ok(book);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
