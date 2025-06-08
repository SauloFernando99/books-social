package com.example.books_social.application.commentary.utils;

import com.example.books_social.application.reply.repository.ReplyDto;

public record ReplyResponse(
    String username,
    ReplyDto reply
) {}
