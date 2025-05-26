package com.example.books_social.domain.model.reply;

import com.example.books_social.domain.model.comentary.CommentaryId;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reply {
    private ReplyId replyId;
    private CommentaryId commentaryId;
    private UUID userId;
    private ReplyText replyText;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Reply(
        ReplyId replyId,
        CommentaryId commentaryId,
        UUID userId,
        ReplyText replyText,
        LocalDateTime createdAt
    ) {
        this.replyId = replyId;
        this.commentaryId = commentaryId;
        this.userId = userId;
        this.replyText = replyText;
        this.createdAt = createdAt;
    }

    public Reply(ReplyId replyId, CommentaryId commentaryId, UUID userId, ReplyText replyText) {
        this.replyId = replyId;
        this.commentaryId = commentaryId;
        this.userId = userId;
        this.replyText = replyText;
    }

    public ReplyId getReplyId() {
        return replyId;
    }

    public void setReplyId(ReplyId replyId) {
        this.replyId = replyId;
    }

    public CommentaryId getCommentaryId() {
        return commentaryId;
    }

    public void setCommentaryId(CommentaryId commentaryId) {
        this.commentaryId = commentaryId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public ReplyText getReplyText() {
        return replyText;
    }

    public void setReplyText(ReplyText replyText) {
        this.replyText = replyText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
