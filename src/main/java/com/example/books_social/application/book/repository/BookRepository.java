package com.example.books_social.application.book.repository;

import java.util.List;
import java.util.UUID;

public interface BookRepository {
    void create(BookDto dto);
    List<BookDto> findAllBooksByOwner(UUID ownerId);
    BookDto findById(UUID bookId);
}
