package com.example.books_social.infrastructure.account;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MongoUserAccountRepository extends MongoRepository<UserAccountDocument, UUID> {
    boolean existsByEmail(String email);
}
