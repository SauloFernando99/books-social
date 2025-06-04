package com.example.books_social.infrastructure.account;

import com.example.books_social.application.account.AccountDto;
import com.example.books_social.domain.model.account.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);
    Optional<AccountDto> findById(UUID id);
}
