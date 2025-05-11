package com.example.books_social.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "book")
@Entity(name = "Book")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String gender;
    private LocalDate date;
    private  LocalDate finished;
    private String review;
    private String favoriteCharacter;
    private  String assessment;
    private String cover;
    private int numberPages;
    private String readingStatus;
    private String bookType;
}
