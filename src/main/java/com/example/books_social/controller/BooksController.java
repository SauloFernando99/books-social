package com.example.books_social.controller;

import com.example.books_social.book.BookData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BooksController {
    @PostMapping
    public void register(@RequestBody BookData data){
        System.out.println(data);
    }
}
