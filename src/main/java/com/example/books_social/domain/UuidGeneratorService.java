package com.example.books_social.domain;

import java.util.UUID;

public interface UuidGeneratorService {
    UUID next();
}