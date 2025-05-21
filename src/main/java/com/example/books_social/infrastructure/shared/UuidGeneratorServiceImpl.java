package com.example.books_social.infrastructure.shared;

import com.example.books_social.domain.services.UuidGeneratorService;

import java.util.UUID;

public class UuidGeneratorServiceImpl implements UuidGeneratorService {

    @Override
    public UUID next() {
        return UUID.randomUUID();
    }
}
