package com.example.books_social.infrastructure.account;

import com.example.books_social.application.account.repository.UserAccountDto;
import com.example.books_social.application.account.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountRepositoryImpl implements UserAccountRepository {
    private final MongoUserAccountRepository innerRepository;

    @Autowired
    public UserAccountRepositoryImpl(MongoUserAccountRepository innerRepository) {
        this.innerRepository = innerRepository;
    }

    @Override
    public void create(UserAccountDto dto) {
        UserAccountDocument document = UserAccountDbMapper.toDocument(dto);
        innerRepository.save(document);
    }

    @Override
    public boolean existsByEmail(String email) {
        return innerRepository.existsByEmail(email);
    }
}
