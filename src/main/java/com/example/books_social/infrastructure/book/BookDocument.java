package com.example.books_social.infrastructure.book;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document(collection = "books")
@EqualsAndHashCode(of = "id")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BookDocument {

    @Id
    private UUID id;
    private UUID ownerId;
    private String title;
    private String author;
    private String genre;
    private LocalDate startDate;
    private LocalDate endDate;
    private String review;
    private String favoriteCharacter;
    private int rating;
    private String coverUrl;
    private int numberPages;
    private String readingStatus;
    private List<String> bookTypes;
    private boolean isFavorite;

    public BookDocument() {}

    public BookDocument(
            UUID id,
            UUID ownerId,
            String title,
            String author,
            String genre,
            LocalDate startDate,
            LocalDate endDate,
            String review,
            String favoriteCharacter,
            int rating,
            String coverUrl,
            int numberPages,
            String readingStatus,
            List<String> bookTypes,
            boolean isFavorite
    ) {
        this.id = UUID.randomUUID();
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
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public String getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(String readingStatus) {
        this.readingStatus = readingStatus;
    }

    public List<String> getBookTypes() {
        return bookTypes;
    }

    public void setBookTypes(List<String> bookTypes) {
        this.bookTypes = bookTypes;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

