package com.example.books_social.domain.model.book;

import com.example.books_social.domain.shared.ddd.Notification;
//import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class Book {

    private BookId bookId;
    private UUID ownerId;
    private String title;
    private String author;
    private Genre genre;
    private LocalDate startDate;
    private LocalDate endDate;
    private String review;
    private String favoriteCharacter;
    private Rating rating;
    private CoverUrl coverUrl;
    private NumberPages numberPages;
    private ReadingStatus readingStatus;
    private List<BookType> bookTypes;
    private boolean isFavorite;

    public Book(
            BookId bookId,
            UUID ownerId,
            String title,
            String author,
            Genre genre,
            LocalDate startDate,
            LocalDate endDate,
            String review,
            String favoriteCharacter,
            Rating rating,
            CoverUrl coverUrl,
            NumberPages numberPages,
            ReadingStatus readingStatus,
            List<BookType> bookTypes,
            boolean isFavorite
    ) {
        this.bookId = bookId;
        this.ownerId = ownerId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.startDate = startDate;
        this.endDate = endDate;
        this.review = review;
        this.favoriteCharacter = favoriteCharacter;
        this.rating = rating;
        this.coverUrl = coverUrl;
        this.numberPages = numberPages;
        this.readingStatus = readingStatus;
        this.bookTypes = bookTypes;
        this.isFavorite = isFavorite;

        Notification notification = validate();
        if (!notification.hasNoErrors()) {
            throw new IllegalArgumentException(notification.message());
        }
    }

    private Notification validate() {
        Notification notification = new Notification();

        if (author == null || author.isBlank()) { notification.addError("Author is required."); }
        if (title == null || title.isBlank()) { notification.addError("Title is required."); }

        return notification;
    }
}
