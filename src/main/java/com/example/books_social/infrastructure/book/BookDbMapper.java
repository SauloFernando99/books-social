package com.example.books_social.infrastructure.book;

import com.example.books_social.application.book.repository.BookDto;

public class BookDbMapper {
    public static BookDocument toDocument (BookDto dto) {
        return new BookDocument(
            dto.bookId(),
            dto.ownerId(),
            dto.title(),
            dto.author(),
            dto.genre(),
            dto.startDate(),
            dto.endDate(),
            dto.review(),
            dto.favoriteCharacter(),
            dto.rating(),
            dto.coverUrl(),
            dto.numberPages(),
            dto.readingStatus(),
            dto.bookTypes(),
            dto.isFavorite(),
            dto.createdAt()
        );
    }

    public static BookDto toDto (BookDocument document) {
        return new BookDto(
            document.getId(),
            document.getOwnerId(),
            document.getTitle(),
            document.getAuthor(),
            document.getGenre(),
            document.getStartDate(),
            document.getEndDate(),
            document.getReview(),
            document.getFavoriteCharacter(),
            document.getRating(),
            document.getCoverUrl(),
            document.getNumberPages(),
            document.getReadingStatus(),
            document.getBookTypes(),
            document.isFavorite(),
            document.getCreatedAt()
        );
    }

}
