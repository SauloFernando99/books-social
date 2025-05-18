package com.example.books_social.controller;

import com.example.books_social.user.User;
import com.example.books_social.user.UserData;
import com.example.books_social.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity register(@RequestBody UserData data) {
        if (repository.existsByEmail(data.email())) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.email(), encryptedPassword);
        repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}