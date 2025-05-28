package com.example.books_social.infrastructure.account;

import com.example.books_social.application.account.repository.UserAccountDto;
import com.example.books_social.domain.model.account.AccountCredentials;
import com.example.books_social.domain.model.account.Authority;
import com.example.books_social.domain.model.account.Username;

import java.util.stream.Collectors;

public class UserAccountDbMapper {

    public static UserAccountDocument toDocument(UserAccountDto dto) {
        AccountCredentials credentials = new AccountCredentials(
                new Username(dto.username()),
                dto.password(),
                dto.authorities().stream()
                        .map(String::toUpperCase)
                        .map(Authority::valueOf)
                        .collect(Collectors.toSet()),
                dto.refreshToken(),
                dto.accountNonExpired(),
                dto.accountNonLocked(),
                dto.credentialsNonExpired(),
                dto.enabled()
        );

        return new UserAccountDocument(
                dto.id(),
                dto.createdAt(),
                dto.email(),
                credentials
        );
    }

    public static UserAccountDto toDto(UserAccountDocument document) {
        AccountCredentials credentials = document.getAccountCredentials();

        return new UserAccountDto(
                document.getId(),
                document.getEmail(),
                document.getCreatedAt(),
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
