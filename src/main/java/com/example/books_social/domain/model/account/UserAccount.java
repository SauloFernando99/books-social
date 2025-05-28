package com.example.books_social.domain.model.account;

import java.time.LocalDateTime;
import java.util.Set;

public class UserAccount {
    private UserAccountId userAccountId;
    private LocalDateTime createdAt;
    private Email email;
    private AccountCredentials accountCredentials;

    public UserAccount(
        UserAccountId userAccountId,
        Email email,
        AccountCredentials accountCredentials,
        Username username,
        String password,
        Set<Authority> authorities
    ) {
        this.userAccountId = userAccountId;
        this.email = email;
        this.createdAt = LocalDateTime.now();
        this.accountCredentials = new AccountCredentials(username, password, authorities);
    }

    public void changeUsername(Username newUsername) {
        this.accountCredentials = new AccountCredentials(
                newUsername,
                accountCredentials.getPassword(),
                accountCredentials.getAuthorities()
        );
    }

    public void changePassword(String newPassword) {
        this.accountCredentials = new AccountCredentials(
                accountCredentials.getUsername(),
                newPassword,
                accountCredentials.getAuthorities()
        );
    }

    public UserAccountId getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(UserAccountId userAccountId) {
        this.userAccountId = userAccountId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public AccountCredentials getAccountCredentials() {
        return accountCredentials;
    }

    public void setAccountCredentials(AccountCredentials accountCredentials) {
        this.accountCredentials = accountCredentials;
    }
}
