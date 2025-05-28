package com.example.books_social.application.account.repository;

import com.example.books_social.domain.model.account.*;

import java.util.Set;
import java.util.stream.Collectors;

public class UserAccountMapper {

    public static UserAccount fromDto(UserAccountDto dto) {
        UserAccountId userAccountId = new UserAccountId(dto.id());
        Email email = new Email(dto.email());
        Username username = new Username(dto.username());
        Set<Authority> authorities = dto.authorities().stream()
                .map(String::toUpperCase)
                .map(Authority::valueOf)
                .collect(Collectors.toSet());

        AccountCredentials credentials = new AccountCredentials(
                username,
                dto.password(),
                authorities,
                dto.refreshToken(),
                dto.accountNonExpired(),
                dto.accountNonLocked(),
                dto.credentialsNonExpired(),
                dto.enabled()
        );

        return new UserAccount(
                userAccountId,
                email,
                credentials,
                username,
                dto.password(),
                authorities
        );
    }

    public static UserAccountDto toDto(UserAccount user) {
        AccountCredentials credentials = user.getAccountCredentials();

        return new UserAccountDto(
                user.getUserAccountId().value(),
                user.getEmail().toString(),
                user.getCreatedAt(),
                credentials.getUsername().toString(),
                credentials.getPassword(),
                credentials.getAuthorities().stream()
                        .map(Enum::name)
                        .collect(Collectors.toSet()),
                credentials.getRefreshToken(),
                credentials.isAccountNonExpired(),
                credentials.isAccountNonLocked(),
                credentials.isCredentialsNonExpired(),
                credentials.isEnabled()
        );
    }
}

