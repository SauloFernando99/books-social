package com.example.books_social.domain.model.book;

import com.example.books_social.domain.shared.ddd.Notification;
//import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime createdAt = LocalDateTime.now();

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
            boolean isFavorite,
            LocalDateTime createdAt
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
        this.createdAt = createdAt;

        Notification notification = validate();
        if (!notification.hasNoErrors()) {
            throw new IllegalArgumentException(notification.message());
        }
    }

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

    public BookId getBookId() {
        return bookId;
    }

    public void setBookId(BookId bookId) {
        this.bookId = bookId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getFavoriteCharacter() {
        return favoriteCharacter;
    }

    public void setFavoriteCharacter(String favoriteCharacter) {
        this.favoriteCharacter = favoriteCharacter;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public CoverUrl getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(CoverUrl coverUrl) {
        this.coverUrl = coverUrl;
    }

    public NumberPages getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(NumberPages numberPages) {
        this.numberPages = numberPages;
    }

    public ReadingStatus getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(ReadingStatus readingStatus) {
        this.readingStatus = readingStatus;
    }

    public List<BookType> getBookTypes() {
        return bookTypes;
    }

    public void setBookTypes(List<BookType> bookTypes) {
        this.bookTypes = bookTypes;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
