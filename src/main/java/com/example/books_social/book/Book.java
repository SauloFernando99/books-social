package com.example.books_social.book;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "book")
@Entity(name = "Book")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String gender;
    private LocalDate date;
    private LocalDate finished;
    private String review;
    private String favoriteCharacter;
    private  String assessment;
    private String cover;
    private int numberPages;
    private String readingStatus;
    private String bookType;

    public Book(){}

    public Book(BookData data){
        this.title = data.title();
        this.gender = data.gender();
        this.date = data.date();
        this.finished = data.finished();
        this.review = data.review();
        this.favoriteCharacter = data.favoriteCharacter();;
        this.assessment = data.assessment();
        this.cover = data.cover();
        this.numberPages = data.numberPages();;
        this.readingStatus = data.readingStatus();
        this.bookType = data.bookType();
    }
}
