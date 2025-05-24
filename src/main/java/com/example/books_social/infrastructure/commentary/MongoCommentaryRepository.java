package com.example.books_social.infrastructure.commentary;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MongoCommentaryRepository extends MongoRepository<CommentaryDocument, UUID> {
}
