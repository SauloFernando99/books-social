package com.example.books_social.infrastructure.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MongoBookRepository extends MongoRepository<BookDocument, UUID> {

    List<BookDocument> findAllByOwnerIdOrderByCreatedAtDesc(UUID ownerId);
    boolean existsByOwnerIdAndTitle(UUID ownerId, String title);
    Optional<BookDocument> findTopByOwnerIdAndReadingStatusOrderByNumberPagesDesc(UUID ownerId, String readingStatus);
    Optional<BookDocument> findTopByOwnerIdAndReadingStatusOrderByNumberPagesAsc(UUID ownerId, String readingStatus);
    List<BookDocument> findAllByOwnerIdAndReadingStatus(UUID ownerId, String readingStatus);
}
