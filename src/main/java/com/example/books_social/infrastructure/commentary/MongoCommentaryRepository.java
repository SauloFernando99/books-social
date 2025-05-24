package com.example.books_social.infrastructure.commentary;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MongoCommentaryRepository extends MongoRepository<CommentaryDocument, UUID> {
}
