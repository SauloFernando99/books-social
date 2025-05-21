package com.example.books_social.application.book.create;

import com.example.books_social.application.book.repository.BookMapper;
import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.domain.services.UuidGeneratorService;
import com.example.books_social.domain.model.book.Book;
import com.example.books_social.domain.model.book.BookId;

public class CreateBookServiceImpl implements CreateBookService {
    private final BookRepository bookRepository;
    private final UuidGeneratorService uuidGeneratorService;

    CreateBookServiceImpl(
      BookRepository bookRepository,
      UuidGeneratorService uuidGeneratorService
    ) {
        this.bookRepository = bookRepository;
        this.uuidGeneratorService = uuidGeneratorService;
    }

    @Override
    public void createBook(CreateBookPresenter presenter, RequestModel request) {
        BookId bookId = new BookId(uuidGeneratorService.next());
        Book book = BookMapper.fromRequestModel(bookId, request);

        bookRepository.create(BookMapper.toDto(book));

        presenter.prepareSuccessView(new ResponseModel(
            bookId.getValue(),
            book.getTitle(),
            book.getCreatedAt()
        ));

    }

}
