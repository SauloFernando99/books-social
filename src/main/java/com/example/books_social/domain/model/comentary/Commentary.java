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

    public Commentary(
        CommentaryId commentaryId,
        BookId bookId, UUID userId,
        CommentaryText commentaryText,
        Progress progress,
        Reaction reaction,
        LocalDateTime createdAt
    ) {
        this.commentaryId = commentaryId;
        this.bookId = bookId;
        this.userId = userId;
        this.commentaryText = commentaryText;
        this.progress = progress;
        this.reaction = reaction;
        this.createdAt = createdAt;
    }

    public CommentaryId getCommentaryId() {
        return commentaryId;
    }

    public void setCommentaryId(CommentaryId commentaryId) {
        this.commentaryId = commentaryId;
    }

    public BookId getBookId() {
        return bookId;
    }

    public void setBookId(BookId bookId) {
        this.bookId = bookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public CommentaryText getCommentaryText() {
        return commentaryText;
    }

    public void setCommentaryText(CommentaryText commentaryText) {
        this.commentaryText = commentaryText;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
