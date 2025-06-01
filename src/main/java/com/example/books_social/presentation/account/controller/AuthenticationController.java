package com.example.books_social.presentation.account.controller;

import com.example.books_social.domain.model.account.Account;
import com.example.books_social.application.account.AccountDto;
import com.example.books_social.presentation.security.BooksTokenService;
import com.example.books_social.presentation.security.JwtTokenData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private BooksTokenService tokenService;

    @PostMapping
    public ResponseEntity<LoginResponse> signIn(@RequestBody @Valid LoginRequest data) {
        try {
            var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = manager.authenticate(token);
            var account = (Account) authentication.getPrincipal();
            var jwtToken = tokenService.generateToken(account);

            var response = new LoginResponse(
                    jwtToken,
                    account.getEmail(),
                    account.getUsername(),
                    account.getUserPhoto(),
                    account.getId()
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 imprime no console
            return ResponseEntity.status(403).build();
        }
    }
}
