package com.example.books_social.application.book.repository;

import com.example.books_social.application.book.create.CreateBookService;
import com.example.books_social.application.book.update.service.UpdateBookService;
import com.example.books_social.domain.model.book.*;
import com.example.books_social.presentation.book.requests.PutRequest;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

    public static Book fromRequestModel(BookId bookId, CreateBookService.RequestModel requestModel) {
        Genre genre = Genre.valueOf(requestModel.genre());
        ReadingStatus readingStatus = ReadingStatus.valueOf(requestModel.readingStatus());
        List<BookType> bookTypes = parseBookTypes(requestModel.bookTypes());
        return new Book(
            bookId,
            requestModel.ownerId(),
            requestModel.title(),
            requestModel.author(),
            genre,
            requestModel.startDate(),
            requestModel.endDate(),
            requestModel.review(),
            requestModel.favoriteCharacter(),
            new Rating(requestModel.rating()),
            new CoverUrl(requestModel.coverUrl()),
            new NumberPages(requestModel.numberPages()),
            readingStatus,
            bookTypes,
            requestModel.isFavorite()
        );
    }

    public static UpdateBookService.RequestModel toUpdatedRequestModel(UUID bookId, PutRequest request) {
        return new UpdateBookService.RequestModel(
                bookId,
                request.title(),
                request.author(),
                request.genre(),
                request.startDate(),
                request.endDate(),
                request.review(),
                request.favoriteCharacter(),
                request.rating(),
                request.coverUrl(),
                request.numberPages(),
                request.readingStatus(),
                request.bookTypes(),
                request.isFavorite()
        );
    }

    public static Book updateFrom(BookDto original, UpdateBookService.RequestModel request) {
        Genre genre = Genre.valueOf(request.genre() != null ? request.genre() : original.genre());
        Rating rating = new Rating(request.rating() != null ? request.rating() : original.rating());
        CoverUrl coverUrl = new CoverUrl(request.coverUrl() != null ? request.coverUrl() : original.coverUrl());
        NumberPages numberPages = new NumberPages(request.numberPages() != null ? request.numberPages() : original.numberPages());
        ReadingStatus readingStatus = ReadingStatus.valueOf(request.readingStatus() != null ? request.readingStatus() : original.readingStatus());
        List<BookType> bookTypes = parseBookTypes(request.bookTypes() != null ? request.bookTypes() : original.bookTypes());
        return new Book(
                new BookId(original.bookId()),
                original.ownerId(),
                request.title() != null ? request.title() : original.title(),
                request.author() != null ? request.author() : original.author(),
                genre,
                request.startDate() != null ? request.startDate() : original.startDate(),
                request.endDate() != null ? request.endDate() : original.endDate(),
                request.review() != null ? request.review() : original.review(),
                request.favoriteCharacter() != null ? request.favoriteCharacter() : original.favoriteCharacter(),
                rating,
                coverUrl,
                numberPages,
                readingStatus,
                bookTypes,
                request.isFavorite(),
                original.createdAt()
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
