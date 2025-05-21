package com.example.books_social.presentation.account;

import com.example.books_social.domain.model.account.Account;
import com.example.books_social.application.account.AccountDto;
import com.example.books_social.infrastructure.security.BooksTokenService;
import com.example.books_social.infrastructure.security.JwtTokenData;
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
    public ResponseEntity signIn(@RequestBody @Valid AccountDto data){
        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(token);
        var jwtToken = tokenService.generateToken((Account) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtTokenData(jwtToken));
    }
}
