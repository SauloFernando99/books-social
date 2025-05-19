package com.example.books_social.infrastructure.configuration;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {
    public static final Dotenv dotenv = Dotenv.configure()
            .directory("./config")
            .load();

    public static String get(String key) {
        return dotenv.get(key);
    }
}