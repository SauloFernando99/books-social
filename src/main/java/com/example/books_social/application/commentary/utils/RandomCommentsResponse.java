package com.example.books_social.application.commentary.utils;

import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.reply.repository.ReplyDto;

import java.util.List;

public record RandomCommentsResponse(
        String username,
        boolean isLiked,
        BookDto book,
        CommentaryDto commentary,
        List<ReplyResponse> replies
) { }

