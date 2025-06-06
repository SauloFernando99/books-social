package com.example.books_social.presentation.book.controller;

import com.example.books_social.application.book.create.CreateBookService.RequestModel;
import com.example.books_social.application.book.create.CreateBookServiceImpl;
import com.example.books_social.application.book.delete.DeleteBookService;
import com.example.books_social.application.book.find.services.FindAllBooksService;
import com.example.books_social.application.book.find.services.FindAllBooksServiceImpl;
import com.example.books_social.application.book.find.services.FindBookService;
import com.example.books_social.application.book.find.services.FindBookServiceImpl;
import com.example.books_social.application.book.repository.BookMapper;
import com.example.books_social.application.book.update.presenter.UpdateBookPresenter;
import com.example.books_social.application.book.update.service.UpdateBookService;
import com.example.books_social.presentation.book.presenter.*;
import com.example.books_social.presentation.book.requests.PostRequest;
import com.example.books_social.presentation.book.requests.PutRequest;
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
    private final FindBookService findBookService;
    private final UpdateBookService updateBookService;
    private final DeleteBookService deleteBookService;

    @Autowired
    public BooksController(
            CreateBookServiceImpl createBookService,
            FindAllBooksServiceImpl findAllBooksService,
            FindBookServiceImpl findBookService,
            UpdateBookService updateBookService,
            DeleteBookService deleteBookService
    ) {
        this.createBookService = createBookService;
        this.findAllBooksService = findAllBooksService;
        this.findBookService = findBookService;
        this.updateBookService = updateBookService;
        this.deleteBookService = deleteBookService;
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

    @GetMapping("/find/{bookId}")
    public ResponseEntity<?> findBook(@PathVariable UUID bookId) {
        RestfulFindBookPresenter presenter = new RestfulFindBookPresenter();
        FindBookService.RequestModel request = new FindBookService.RequestModel(bookId);

        findBookService.findBook(presenter, request);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable UUID bookId, @RequestBody PutRequest request) {
        RestfulUpdateBookPresenter presenter = new RestfulUpdateBookPresenter();
        UpdateBookService.RequestModel viewModel = BookMapper.toUpdatedRequestModel(bookId, request);

        updateBookService.updateBook(presenter, viewModel);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID bookId) {
        RestfulDeleteBookPresenter presenter = new RestfulDeleteBookPresenter();
        DeleteBookService.RequestModel viewModel = new DeleteBookService.RequestModel(bookId);

        deleteBookService.deleteBook(presenter, viewModel);

        return presenter.getResponseEntity() != null
                ? presenter.getResponseEntity()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
