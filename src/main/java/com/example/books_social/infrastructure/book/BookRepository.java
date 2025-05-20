package com.example.books_social.infrastructure.book;

import com.example.books_social.domain.model.book.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends MongoRepository<BookDocument, UUID> {
}
