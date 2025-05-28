package com.example.books_social.infrastructure.account;

import com.example.books_social.domain.model.account.AccountCredentials;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "user_accounts")
@EqualsAndHashCode(of = "id")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserAccountDocument {
    @Id
    private UUID id;
    private LocalDateTime createdAt;
    private String email;
    private String userPhoto;
    private AccountCredentials accountCredentials;

    UserAccountDocument() {}

    public UserAccountDocument(UUID id, LocalDateTime createdAt, String email, String userPhoto, AccountCredentials accountCredentials) {
        this.id = id;
        this.createdAt = createdAt;
        this.email = email;
        this.userPhoto = userPhoto;
        this.accountCredentials = accountCredentials;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public AccountCredentials getAccountCredentials() {
        return accountCredentials;
    }

    public void setAccountCredentials(AccountCredentials accountCredentials) {
        this.accountCredentials = accountCredentials;
    }
}