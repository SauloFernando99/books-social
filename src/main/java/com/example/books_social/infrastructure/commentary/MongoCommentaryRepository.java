package com.example.books_social.infrastructure.commentary;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MongoCommentaryRepository extends MongoRepository<CommentaryDocument, UUID> {
    List<CommentaryDocument> findAllCommentsByBookId(UUID bookId);
    CommentaryDocument findByCommentaryId(UUID commentaryId);
}
