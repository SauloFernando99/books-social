package com.example.books_social.infrastructure.reply;

import com.example.books_social.application.reply.repository.ReplyDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MongoReplyRepository extends MongoRepository<ReplyDocument, UUID> {
    List<ReplyDocument> findAllByCommentaryId(UUID commentaryId);
}
