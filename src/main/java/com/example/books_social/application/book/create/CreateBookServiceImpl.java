package com.example.books_social.application.book.create;

import com.example.books_social.application.book.repository.BookMapper;
import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;
import com.example.books_social.application.shared.exceptions.UniquenessViolationException;
import com.example.books_social.domain.services.UuidGeneratorService;
import com.example.books_social.domain.model.book.Book;
import com.example.books_social.domain.model.book.BookId;

public class CreateBookServiceImpl implements CreateBookService {
    private final BookRepository bookRepository;
    private final UuidGeneratorService uuidGeneratorService;

    public CreateBookServiceImpl(
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
        boolean exists = bookRepository.existsByOwnerIdAndTitle(request.ownerId(), request.title());

        if (exists) {
            String message = "This user already has a book of title: " + request.title() + ".";
            presenter.prepareFailView(new UniquenessViolationException(message));
            return;
        }

        bookRepository.create(BookMapper.toDto(book));

        presenter.prepareSuccessView(new ResponseModel(
            bookId.getValue(),
            book.getTitle(),
            book.getCreatedAt()
        ));

    }

}
