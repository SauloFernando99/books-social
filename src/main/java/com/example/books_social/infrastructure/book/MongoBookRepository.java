package com.example.books_social.infrastructure.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MongoBookRepository extends MongoRepository<BookDocument, UUID> {

    List<BookDocument> findAllByOwnerIdOrderByCreatedAtAsc(UUID ownerId);
}
