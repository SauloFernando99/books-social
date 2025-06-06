package com.example.books_social.application.commentary.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CommentaryDto(
        UUID commentaryId,
        UUID bookId,
        UUID userId,
        String commentaryText,
        Integer readPages,
        Integer progress,
        String reaction,
        Integer likes,
        List<UUID> likesList,
        LocalDateTime createdAt
) {}
