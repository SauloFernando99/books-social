package com.example.books_social.application.reply.repository;

import java.util.List;
import java.util.UUID;

public interface ReplyRepository {
    void create(ReplyDto dto);
    List<ReplyDto> findAllByCommentaryId(UUID commentaryId);
}
