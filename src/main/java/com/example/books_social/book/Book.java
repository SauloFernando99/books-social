package com.example.books_social.book;

import java.time.LocalDate;

public record Book (
        int id,
        String title,
        String gender,
        LocalDate date,
        LocalDate finished,
        String review,
        String favoriteCharacter,
        String assessment,
        String cover,
        int numberPages,
        String readingStatus,
        String bookType) {

}
