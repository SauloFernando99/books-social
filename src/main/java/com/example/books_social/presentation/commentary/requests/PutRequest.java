package com.example.books_social.presentation.commentary.requests;

public record PutRequest(
    String commentaryText,
    Integer readPages,
    String reaction,
    boolean isSpoiler
) {
}
