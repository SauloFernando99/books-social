package com.example.books_social.infrastructure.book;

import com.example.books_social.domain.model.book.Book;
import com.example.books_social.application.book.repository.BookDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book")
public class BooksController {
    @Autowired
    private BookRepository repository;

    @PostMapping
    public void register(@RequestBody @Valid BookDto data){

        BookDocument document = BookDbMapper.toDocument(data);

        repository.save(document);
    }

//    @GetMapping
//    public List<Book> list() {
//        return repository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
//        Optional<Book> bookOptional = repository.findById(id);
//
//        if (bookOptional.isPresent()) {
//            Book book = bookOptional.get();
//            return ResponseEntity.ok(book);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
