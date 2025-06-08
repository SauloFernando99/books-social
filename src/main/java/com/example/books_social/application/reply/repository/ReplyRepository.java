package com.example.books_social.application.reply.repository;

import java.util.List;
import java.util.UUID;

public interface ReplyRepository {
    void create(ReplyDto dto);
    List<ReplyDto> findAllByCommentaryId(UUID commentaryId);
    ReplyDto findById(UUID replyId);
    void deleteById(UUID replyId);
    boolean existsById(UUID replyId);
}
