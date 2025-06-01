package com.example.books_social.presentation.account.controller;

import com.example.books_social.domain.model.account.Account;
import com.example.books_social.application.account.AccountDto;
import com.example.books_social.infrastructure.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity register(@RequestBody AccountDto data) {
        if (repository.existsByEmail(data.email())) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        UUID id = UUID.randomUUID();
        Account newAccount = new Account(id, data.username(), data.email(), data.userPhoto(), encryptedPassword);
        repository.save(newAccount);

        return ResponseEntity.ok().build();
    }
}