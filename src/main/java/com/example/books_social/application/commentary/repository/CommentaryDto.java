package com.example.books_social.application.commentary.repository;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentaryDto(
        UUID commentaryId,
        UUID bookId,
        UUID userId,
        String commentaryText,
        Integer progress,
        String reaction,
        LocalDateTime createdAt
) {}
