package com.example.books_social.application.account.repository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserAccountDto(
    UUID id,
    String email,
    LocalDateTime createdAt,

    String username,
    String password,
    Set<String> authorities,

    String refreshToken,

    boolean accountNonExpired,
    boolean accountNonLocked,
    boolean credentialsNonExpired,
    boolean enabled
) { }

