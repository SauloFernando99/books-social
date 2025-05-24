package com.example.books_social.presentation.commentary.requests;

import java.util.UUID;

public record PostRequest(
    UUID bookId,
    UUID userId,
    String commentaryText,
    Integer progress,
    String reaction
) {}
