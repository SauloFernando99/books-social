package com.example.books_social.presentation.book.controller;

import com.example.books_social.application.book.create.CreateBookService.RequestModel;
import com.example.books_social.application.book.create.CreateBookServiceImpl;
import com.example.books_social.presentation.book.presenter.RestfulCreateBookPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/book")
public class BooksController {

    private final CreateBookServiceImpl createBookService;

    @Autowired
    public BooksController(CreateBookServiceImpl createBookService) {
        this.createBookService = createBookService;
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Map<String, Object> jsonData) {
        RestfulCreateBookPresenter presenter = new RestfulCreateBookPresenter();

        UUID ownerId = UUID.fromString((String) jsonData.get("ownerId"));
        String title = (String) jsonData.get("title");
        String author = (String) jsonData.get("author");
        String genre = (String) jsonData.get("genre");
        LocalDate startDate = LocalDate.parse((String) jsonData.get("startDate"));
        LocalDate endDate = LocalDate.parse((String) jsonData.get("endDate"));
        String review = (String) jsonData.get("review");
        String favoriteCharacter = (String) jsonData.get("favoriteCharacter");
        Integer rating = (Integer) jsonData.get("rating");
        String coverUrl = (String) jsonData.get("coverUrl");
        Integer numberPages = (Integer) jsonData.get("numberPages");
        String readingStatus = (String) jsonData.get("readingStatus");
        List<String> bookTypes = (List<String>) jsonData.get("bookTypes");
        boolean isFavorite = (boolean) jsonData.get("isFavorite");

        RequestModel request = new RequestModel(
                ownerId,
                title,
                author,
                genre,
                startDate,
                endDate,
                review,
                favoriteCharacter,
                rating,
                coverUrl,
                numberPages,
                readingStatus,
                bookTypes,
                isFavorite
        );

        createBookService.createBook(presenter, request);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
