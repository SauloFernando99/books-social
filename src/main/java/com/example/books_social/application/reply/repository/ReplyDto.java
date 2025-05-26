package com.example.books_social.application.reply.repository;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReplyDto(
    UUID replyId,
    UUID commentaryId,
    UUID userId,
    String replyText,
    LocalDateTime createdAt
) {
}
