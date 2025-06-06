package com.example.books_social.infrastructure.commentary;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document("comments")
@EqualsAndHashCode(of = "id")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CommentaryDocument {
    @Id
    private UUID id;
    private UUID bookId;
    private UUID userId;
    private String commentaryText;
    private Integer readPages;
    private Integer progress;
    private String reaction;
    private Integer likes;
    private List<UUID> likesList;
    private LocalDateTime createdAt;

    public CommentaryDocument(
        UUID id,
        UUID bookId,
        UUID userId,
        String commentaryText,
        Integer readPages,
        Integer progress,
        String reaction,
        Integer likes,
        List<UUID> likesList,
        LocalDateTime createdAt
    ) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.commentaryText = commentaryText;
        this.readPages = readPages;
        this.progress = progress;
        this.reaction = reaction;
        this.likes = likes;
        this.likesList = likesList;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getCommentaryText() {
        return commentaryText;
    }

    public void setCommentaryText(String commentaryText) {
        this.commentaryText = commentaryText;
    }

    public Integer getReadPages() {
        return readPages;
    }

    public void setReadPages(Integer readPages) {
        this.readPages = readPages;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
