package com.example.books_social.domain.model.account;
import java.util.Set;

public class AccountCredentials {

    private String username;
    private String password;
    private Set<Authority> authorities;

    private String refreshToken;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public AccountCredentials(
            String username,
            String password,
            Set<Authority> authorities
    ) {
        this(username, password, authorities, null, true, true, true, true);
    }

    public AccountCredentials(
            String username,
            String password,
            Set<Authority> authorities,
            String refreshToken,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired,
            boolean isEnabled
    ) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.refreshToken = refreshToken;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}

