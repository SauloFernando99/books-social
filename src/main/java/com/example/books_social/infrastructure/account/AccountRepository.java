package com.example.books_social.infrastructure.account;

import com.example.books_social.domain.model.account.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    UserDetails findByUsername(String email);

    boolean existsByEmail(String email);
}
