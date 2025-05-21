package com.example.books_social.application.book.repository;

import com.example.books_social.domain.model.book.*;

import java.util.Collections;
import java.util.List;

public class BookMapper {

    public static Book fromDto(BookDto dto) {
        Genre genre = Genre.valueOf(dto.genre());
        ReadingStatus readingStatus = ReadingStatus.valueOf(dto.readingStatus());
        List<BookType> bookTypes = parseBookTypes(dto.bookTypes());

        return new Book (
                new BookId(dto.bookId()),
                dto.ownerId(),
                dto.title(),
                dto.author(),
                genre,
                dto.startDate(),
                dto.endDate(),
                dto.review(),
                dto.favoriteCharacter(),
                new Rating(dto.rating()),
                new CoverUrl(dto.coverUrl()),
                new NumberPages(dto.numberPages()),
                readingStatus,
                bookTypes,
                dto.isFavorite(),
                dto.createdAt()
        );
    }

    public static BookDto toDto(Book book) {
        return new BookDto(
            book.getBookId().getValue(),
            book.getOwnerId(),
            book.getTitle(),
            book.getAuthor(),
            book.getGenre().name()  ,
            book.getStartDate(),
            book.getEndDate(),
            book.getReview(),
            book.getFavoriteCharacter(),
            book.getRating().value(),
            book.getCoverUrl().toString(),
            book.getNumberPages().value(),
            book.getReadingStatus().name(),
            book.getBookTypes().stream().map(Enum::name).toList(),
            book.isFavorite(),
            book.getCreatedAt()
        );
    }

    public static List<BookType> parseBookTypes(List<String> values) {
        if (values == null) return Collections.emptyList();

        return values.stream()
                .map(value -> {
                    try {
                        return BookType.valueOf(value.toUpperCase());
                    } catch (IllegalArgumentException | NullPointerException e) {
                        throw new IllegalArgumentException("Invalid book type: " + value);
                    }
                })
                .toList();
    }

}
