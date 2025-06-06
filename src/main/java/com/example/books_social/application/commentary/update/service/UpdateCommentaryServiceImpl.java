package com.example.books_social.application.commentary.update.service;

import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.repository.CommentaryMapper;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.commentary.update.presenter.UpdateCommentaryPresenter;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;
import com.example.books_social.domain.model.comentary.Commentary;

public class UpdateCommentaryServiceImpl implements UpdateCommentaryService {
    private final CommentaryRepository repository;
    private final BookRepository bookRepository;

    public UpdateCommentaryServiceImpl(
        CommentaryRepository repository,
        BookRepository bookRepository
    ) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void updateCommentary(UpdateCommentaryPresenter presenter, RequestModel request) {
        CommentaryDto original = repository.findCommentary(request.commentaryId());

        if (original == null) {
            String message = "There is no commentary of id: " + request.commentaryId() + ".";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }

        BookDto bookDto = bookRepository.findById(original.bookId());

        int readPages = request.readPages();
        int totalPages = bookDto.numberPages();
        int progressPercent = (int) ((readPages / (float) totalPages) * 100);


        Commentary updatedCommentary = CommentaryMapper.updateFrom(original, progressPercent, request);
        CommentaryDto updated = CommentaryMapper.toDto(updatedCommentary);

        if (!updated.equals(original)) {
            repository.saveOrUpdate(updated);
        }

        presenter.prepareSuccessView(new ResponseModel(updated.commentaryId(), updated.bookId(), updated));
    }
}
