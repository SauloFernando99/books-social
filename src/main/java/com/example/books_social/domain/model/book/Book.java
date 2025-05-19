package com.example.books_social.domain.model.book;

import com.example.books_social.application.book.BookDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "books")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Book {

    @Id
    private Long id;
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
    private String bookType;

    public Book(){}

    public Book(BookDto data){
        this.title = data.title();
        this.author = data.author();
        this.genre = data.gender();
        this.startDate = data.startDate();
        this.endDate = data.endDate();
        this.review = data.review();
        this.favoriteCharacter = data.favoriteCharacter();;
        this.rating = data.rating();
        this.coverUrl = data.cover();
        this.numberPages = data.numberPages();;
        this.readingStatus = data.readingStatus();
        this.bookType = data.bookType();
    }
}
