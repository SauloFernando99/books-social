package com.example.books_social.infrastructure.book;

import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final MongoBookRepository repository;

    @Autowired
    public BookRepositoryImpl(MongoBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(BookDto dto) {
        BookDocument document = BookDbMapper.toDocument(dto);
        repository.save(document);
    }
}
