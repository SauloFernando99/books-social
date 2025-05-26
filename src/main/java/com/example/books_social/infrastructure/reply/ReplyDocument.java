package com.example.books_social.infrastructure.reply;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("replies")
@EqualsAndHashCode(of = "id")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReplyDocument {
    @Id
    private UUID id;
    private UUID commentaryId;
    private UUID userId;
    private String replyText;
    private LocalDateTime createdAt;

    public ReplyDocument(UUID id, UUID commentaryId, UUID userId, String replyText, LocalDateTime createdAt) {
        this.id = id;
        this.commentaryId = commentaryId;
        this.userId = userId;
        this.replyText = replyText;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCommentaryId() {
        return commentaryId;
    }

    public void setCommentaryId(UUID commentaryId) {
        this.commentaryId = commentaryId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
