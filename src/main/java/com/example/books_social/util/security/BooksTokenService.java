package com.example.books_social.util.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.books_social.user.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class BooksTokenService {
    public String generateToken (User user) {
        try {
            var algorithm =  Algorithm.HMAC256("12345678");
             String token = JWT.create()
                     .withIssuer("LORDOFTHERINGS")
                     .withSubject(user.getUsername())
                     .withExpiresAt(expirationDate()).sign(algorithm);

             return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error when creating JWT Token", exception);
        }
    }
    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
