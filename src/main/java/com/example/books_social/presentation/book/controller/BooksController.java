package com.example.books_social.presentation.book.controller;

import com.example.books_social.application.book.create.CreateBookService.RequestModel;
import com.example.books_social.application.book.create.CreateBookServiceImpl;
import com.example.books_social.application.book.find.services.FindAllBooksService;
import com.example.books_social.application.book.find.services.FindAllBooksServiceImpl;
import com.example.books_social.application.book.find.services.FindBookService;
import com.example.books_social.presentation.book.presenter.RestfulCreateBookPresenter;
import com.example.books_social.presentation.book.presenter.RestfulFindAllBooksPresenter;
import com.example.books_social.presentation.book.presenter.RestfulFindBookPresenter;
import com.example.books_social.presentation.book.requests.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/book")
public class BooksController {

    private final CreateBookServiceImpl createBookService;
    private final FindAllBooksServiceImpl findAllBooksService;

    @Autowired
    public BooksController(
        CreateBookServiceImpl createBookService,
        FindAllBooksServiceImpl findAllBooksService
        ) {
        this.createBookService = createBookService;
        this.findAllBooksService = findAllBooksService;
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody PostRequest request) {
        RestfulCreateBookPresenter presenter = new RestfulCreateBookPresenter();

        RequestModel model = new RequestModel(
                request.ownerId(),
                request.title(),
                request.author(),
                request.genre(),
                request.startDate(),
                request.endDate(),
                request.review(),
                request.favoriteCharacter(),
                request.rating(),
                request.coverUrl(),
                request.numberPages(),
                request.readingStatus(),
                request.bookTypes(),
                request.isFavorite()
        );

        createBookService.createBook(presenter, model);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @GetMapping("/{ownerId}")
    public ResponseEntity<?> findAllBooks(@PathVariable UUID ownerId) {
        RestfulFindAllBooksPresenter presenter = new RestfulFindAllBooksPresenter();
        FindAllBooksService.RequestModel request = new FindAllBooksService.RequestModel(ownerId);

        findAllBooksService.findAllByOwner(presenter, request);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> findBook(@PathVariable UUID bookId) {
        RestfulFindBookPresenter presenter = new RestfulFindBookPresenter();
        FindBookService.RequestModel request = new FindBookService.RequestModel(bookId);
    }
}
