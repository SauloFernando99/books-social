package com.example.books_social.application.account.repository;

import com.example.books_social.application.account.create.CreateUserAccountService;
import com.example.books_social.domain.model.account.*;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.books_social.domain.model.account.Authority.USER;

public class UserAccountMapper {

    public static UserAccount fromDto(UserAccountDto dto) {
        UserAccountId userAccountId = new UserAccountId(dto.id());
        Email email = new Email(dto.email());
        UserPhoto userPhoto = new UserPhoto(dto.userPhoto());
        Username username = new Username(dto.username());
        Set<Authority> authorities = dto.authorities().stream()
                .map(String::toUpperCase)
                .map(Authority::valueOf)
                .collect(Collectors.toSet());

        AccountCredentials credentials = new AccountCredentials(
                username.toString(),
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
                username,
                dto.password(),
                userPhoto,
                authorities
        );
    }

    public static UserAccountDto toDto(UserAccount user) {
        AccountCredentials credentials = user.getAccountCredentials();

        return new UserAccountDto(
                user.getUserAccountId().value(),
                user.getEmail().toString(),
                user.getCreatedAt(),
                user.getUserPhoto().toString(),
                user.getUserName().toString(),
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
    
    public static UserAccount fromRequestModel (
        UserAccountId userAccountId, CreateUserAccountService.RequestModel request
    ) {
        Set<Authority> authorities = EnumSet.of(Authority.USER);
        return new UserAccount(
            userAccountId,
            new Email(request.email()),
            new Username(request.username()),
            request.password(),
            new UserPhoto(request.userPhoto()),
            authorities
        );
    }
}

