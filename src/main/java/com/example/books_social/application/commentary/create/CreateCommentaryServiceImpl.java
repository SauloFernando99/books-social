package com.example.books_social.application.commentary.create;

import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.commentary.repository.CommentaryMapper;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;
import com.example.books_social.domain.model.comentary.Commentary;
import com.example.books_social.domain.model.comentary.CommentaryId;
import com.example.books_social.domain.services.UuidGeneratorService;

public class CreateCommentaryServiceImpl implements CreateCommentaryService{
    private final CommentaryRepository repository;
    private final BookRepository bookRepository;
    private final UuidGeneratorService uuidGeneratorService;

    public CreateCommentaryServiceImpl(
        CommentaryRepository repository,
        BookRepository bookRepository,
        UuidGeneratorService uuidGeneratorService
    ) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.uuidGeneratorService = uuidGeneratorService;
    }

    @Override
    public void createCommentary(CreateCommentaryPresenter presenter, RequestModel request) {
        CommentaryId commentaryId = new CommentaryId(uuidGeneratorService.next());
        Commentary commentary = CommentaryMapper.fromRequestModel(commentaryId, request);

        boolean bookExists = bookRepository.existsById(request.bookId());

        if (!bookExists) {
            String message = "Book of id: " + request.bookId() + "not exists.";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }

        repository.create(CommentaryMapper.toDto(commentary));
        presenter.prepareSuccessView(
            new ResponseModel(
                commentaryId.getValue(),
                request.userId(),
                commentary.getCreatedAt()
            )
        );
    }
}
