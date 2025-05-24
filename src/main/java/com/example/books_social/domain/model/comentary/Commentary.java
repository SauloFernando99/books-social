package com.example.books_social.domain.model.comentary;

import com.example.books_social.domain.model.book.BookId;

import java.time.LocalDateTime;
import java.util.UUID;

public class Commentary {
    private CommentaryId commentaryId;
    private BookId bookId;
    private UUID userId;
    private CommentaryText commentaryText;
    private Progress progress;
    private Reaction reaction;
    private LocalDateTime createdAt = LocalDateTime.now();

}
