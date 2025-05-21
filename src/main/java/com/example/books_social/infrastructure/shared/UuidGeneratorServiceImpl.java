package com.example.books_social.infrastructure.shared;

import com.example.books_social.domain.services.UuidGeneratorService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidGeneratorServiceImpl implements UuidGeneratorService {

    @Override
    public UUID next() {
        return UUID.randomUUID();
    }
}
