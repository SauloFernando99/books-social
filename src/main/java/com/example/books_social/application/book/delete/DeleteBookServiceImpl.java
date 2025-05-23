package com.example.books_social.application.book.delete;

import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;

public class DeleteBookServiceImpl implements DeleteBookService{
    BookRepository bookRepository;

    public DeleteBookServiceImpl(
        BookRepository bookRepository
    ) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void deleteBook(DeleteBookPresenter presenter, RequestModel request) {

        boolean exists = bookRepository.existsById(request.bookId());

        if (!exists) {
            String message = "There's no book of id: " + request.bookId() + ".";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }

        bookRepository.deleteById(request.bookId());

        presenter.prepareSuccessView(new DeleteBookService.ResponseModel(request.bookId()));
    }
}
