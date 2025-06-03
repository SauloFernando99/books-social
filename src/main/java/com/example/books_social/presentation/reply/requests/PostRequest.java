package com.example.books_social.presentation.reply.requests;

import java.util.UUID;

public record PostRequest(
        UUID commentaryId,
        UUID userId,
        String replyText
) {}