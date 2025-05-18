package com.example.books_social.account;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);
}
