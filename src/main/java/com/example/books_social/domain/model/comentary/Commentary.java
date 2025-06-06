package com.example.books_social.domain.model.comentary;

import com.example.books_social.domain.model.book.BookId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Commentary {
    private CommentaryId commentaryId;
    private BookId bookId;
    private UUID userId;
    private CommentaryText commentaryText;
    private Integer readPages;
    private Progress progress;
    private Reaction reaction;
    private Integer likes = 0;
    private List<UUID> likesList = new ArrayList<>();
    private LocalDateTime createdAt = LocalDateTime.now();

    public Commentary(
        CommentaryId commentaryId,
        BookId bookId, UUID userId,
        CommentaryText commentaryText,
        Integer readPages,
        Progress progress,
        Reaction reaction,
        Integer likes,
        List<UUID> likesList,
        LocalDateTime createdAt
    ) {
        this.commentaryId = commentaryId;
        this.bookId = bookId;
        this.userId = userId;
        this.commentaryText = commentaryText;
        this.readPages = readPages;
        this.progress = progress;
        this.reaction = reaction;
        this.likesList = likesList;
        this.likes = likes;
        this.createdAt = createdAt;
    }

    public Commentary(
        CommentaryId commentaryId,
        BookId bookId,
        UUID userId,
        CommentaryText commentaryText,
        Integer readPages,
        Progress progress,
        Reaction reaction
    ) {
        this.commentaryId = commentaryId;
        this.bookId = bookId;
        this.userId = userId;
        this.commentaryText = commentaryText;
        this.readPages = readPages;
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

    public Integer getReadPages() {
        return readPages;
    }

    public void setReadPages(Integer readPages) {
        this.readPages = readPages;
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

    public List<UUID> getLikesList() {
        return likesList;
    }

    public void setLikesList(List<UUID> likesList) {
        this.likesList = likesList;
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
