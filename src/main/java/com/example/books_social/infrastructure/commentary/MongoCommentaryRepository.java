package com.example.books_social.infrastructure.commentary;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MongoCommentaryRepository extends MongoRepository<CommentaryDocument, UUID> {
    List<CommentaryDocument> findAllCommentsByBookIdOrderByCreatedAtDesc(UUID bookId);

    @Aggregation(pipeline = {
            "{ $sample: { size: 10 } }"
    })
    List<CommentaryDocument> findRandomComments();
}
