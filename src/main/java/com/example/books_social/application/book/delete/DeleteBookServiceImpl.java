package com.example.books_social.application.book.delete;

import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.reply.repository.ReplyRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;

public class DeleteBookServiceImpl implements DeleteBookService{
    BookRepository bookRepository;
    CommentaryRepository commentaryRepository;

    public DeleteBookServiceImpl(
        BookRepository bookRepository,
        CommentaryRepository commentaryRepository
    ) {
        this.bookRepository = bookRepository;
        this.commentaryRepository = commentaryRepository;
    }

    @Override
    public void deleteBook(DeleteBookPresenter presenter, RequestModel request) {

        boolean exists = bookRepository.existsById(request.bookId());

        if (!exists) {
            String message = "There's no book of id: " + request.bookId() + ".";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }
        commentaryRepository.deleteAllByBookId(request.bookId());
        bookRepository.deleteById(request.bookId());

        presenter.prepareSuccessView(new DeleteBookService.ResponseModel(request.bookId()));
    }
}
