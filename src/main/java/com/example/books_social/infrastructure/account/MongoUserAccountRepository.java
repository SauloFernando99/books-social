package com.example.books_social.infrastructure.account;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MongoUserAccountRepository extends MongoRepository<UserAccountDocument, UUID> {
}
