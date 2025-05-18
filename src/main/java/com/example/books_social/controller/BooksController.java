package com.example.books_social.controller;

import com.example.books_social.book.Book;
import com.example.books_social.book.BookData;
import com.example.books_social.book.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = repository.findById(id);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
