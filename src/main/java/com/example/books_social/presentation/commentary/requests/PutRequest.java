package com.example.books_social.presentation.commentary.requests;

public record PutRequest(
    String commentaryText,
    Integer progress,
    String reaction
) {
}
