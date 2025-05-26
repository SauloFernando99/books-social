package com.example.books_social.infrastructure.reply;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MongoReplyRepository extends MongoRepository<ReplyDocument, UUID> {
}
