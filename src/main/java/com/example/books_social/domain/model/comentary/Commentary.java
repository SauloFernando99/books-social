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
    private Integer likes = 0;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Commentary(
        CommentaryId commentaryId,
        BookId bookId, UUID userId,
        CommentaryText commentaryText,
        Progress progress,
        Reaction reaction,
        Integer likes,
        LocalDateTime createdAt
    ) {
        this.commentaryId = commentaryId;
        this.bookId = bookId;
        this.userId = userId;
        this.commentaryText = commentaryText;
        this.progress = progress;
        this.reaction = reaction;
        this.likes = likes;
        this.createdAt = createdAt;
    }

    public Commentary(
        CommentaryId commentaryId,
        BookId bookId,
        UUID userId,
        CommentaryText commentaryText,
        Progress progress,
        Reaction reaction
    ) {
        this.commentaryId = commentaryId;
        this.bookId = bookId;
        this.userId = userId;
        this.commentaryText = commentaryText;
        this.progress = progress;
        this.reaction = reaction;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
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
