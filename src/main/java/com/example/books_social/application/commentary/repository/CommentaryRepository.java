package com.example.books_social.application.commentary.repository;

import com.example.books_social.domain.model.book.BookId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CommentaryRepository {
    void create(CommentaryDto dto);
    List<CommentaryDto> findAllCommentsByBook(UUID bookId);
    CommentaryDto findCommentary(UUID commentaryId);
    boolean existsById(UUID commentaryId);
    void deleteById(UUID commentaryId);
    void saveOrUpdate(CommentaryDto dto);
    List<CommentaryDto> findCommentaryRandomly();
    void deleteAllByBookId (UUID bookId);
}
