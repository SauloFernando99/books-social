package com.example.books_social.infrastructure.commentary;

import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.infrastructure.book.BookDocument;
import com.example.books_social.infrastructure.book.MongoBookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentaryRepositoryImpl implements CommentaryRepository {

    private final MongoCommentaryRepository innerRepository;

    @Autowired
    public CommentaryRepositoryImpl(MongoCommentaryRepository repository) {
        this.innerRepository = repository;
    }

    @Override
    public void create(CommentaryDto dto) {
        CommentaryDocument document = CommentaryDbMapper.toDocument(dto);
        innerRepository.save(document);
    }
}
