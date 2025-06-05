package com.example.books_social.application.book.repository;

import com.example.books_social.application.book.create.CreateBookService;
import com.example.books_social.application.book.update.service.UpdateBookService;
import com.example.books_social.domain.model.book.*;
import com.example.books_social.presentation.book.requests.PutRequest;
import lombok.val;

import java.time.LocalDate;
import java.util.ArrayList;
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
            book.getGenre().name(),
            book.getStartDate(),
            book.getEndDate(),
            book.getReview(),
            book.getFavoriteCharacter(),
            book.getRating() != null ? book.getRating().value() : null,
            book.getCoverUrl() != null ? book.getCoverUrl().value() : null,
            book.getNumberPages() != null ? book.getNumberPages().value() : null,
            book.getReadingStatus().name(),
                book.getBookTypes() != null
                    ? book.getBookTypes().stream().map(Enum::name).toList()
                    : null,
            book.isFavorite(),
            book.getCreatedAt()
        );
    }

    public static Book fromRequestModel(BookId bookId, CreateBookService.RequestModel requestModel) {
        Genre genre = Genre.valueOf(requestModel.genre());
        ReadingStatus readingStatus = ReadingStatus.valueOf(requestModel.readingStatus());
        List<BookType> bookTypes = parseBookTypes(requestModel.bookTypes());

        NumberPages numberPages = requestModel.numberPages() != null
                ? new NumberPages(requestModel.numberPages())
                : null;

        Rating rating = requestModel.rating() != null
                ? new Rating(requestModel.rating())
                : null;

        CoverUrl coverUrl = requestModel.coverUrl() != null && !requestModel.coverUrl().isBlank()
                ? new CoverUrl(requestModel.coverUrl())
                : null;

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
                rating,
                coverUrl,
                numberPages,
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
        Integer ratingValue = request.rating() != null
                ? request.rating()
                : (original.rating() != null ? original.rating() : 1);

        Rating rating = new Rating(ratingValue);
        CoverUrl coverUrl = new CoverUrl(request.coverUrl() != null ? request.coverUrl() : original.coverUrl());
        Integer pages = request.numberPages() != null
                ? request.numberPages()
                : (original.numberPages() != null ? original.numberPages() : 1);

        NumberPages numberPages = new NumberPages(pages);
        ReadingStatus readingStatus = ReadingStatus.valueOf(request.readingStatus() != null ? request.readingStatus() : original.readingStatus());
        List<BookType> bookTypes = parseBookTypes(request.bookTypes() != null ? request.bookTypes() : original.bookTypes());
        Boolean isFavorite = request.isFavorite() != null ? request.isFavorite() : original.isFavorite();
        String review = request.review() != null ? request.review() : original.review();
        LocalDate endDate = request.endDate() != null ? request.endDate() : original.endDate();
        LocalDate startDate = request.startDate() != null ? request.startDate(): original.startDate();
        String favoriteCharacter = request.favoriteCharacter() != null ? request.favoriteCharacter() : original.favoriteCharacter();

        // Se estiver lendo, esses campos devem ser anulados
        if (readingStatus == ReadingStatus.READING) {
            review = null;
            endDate = null;
            favoriteCharacter = null;
            rating = null;
            isFavorite = false;
        }

        // Se estiver na wishlist, esses campos devem ser anulados
        if (readingStatus == ReadingStatus.WISHLIST) {
            review = null;
            startDate = null;
            endDate = null;
            favoriteCharacter = null;
            rating = null;
            isFavorite = false;
            bookTypes = new ArrayList<>();
        }

        return new Book(
                new BookId(original.bookId()),
                original.ownerId(),
                request.title() != null ? request.title() : original.title(),
                request.author() != null ? request.author() : original.author(),
                genre,
                startDate,
                endDate,
                review,
                favoriteCharacter,
                rating,
                coverUrl,
                numberPages,
                readingStatus,
                bookTypes,
                isFavorite,
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
