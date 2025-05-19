package com.example.books_social.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.books_social.domain.model.account.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class BooksTokenService {

    @Value("${JWT_SECRET}")
    private String secret;

    public String generateToken (Account account) {
        try {
            var algorithm =  Algorithm.HMAC256(secret);
             String token = JWT.create()
                     .withIssuer("LORDOFTHERINGS")
                     .withSubject(account.getUsername())
                     .withExpiresAt(expirationDate()).sign(algorithm);

             return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error when creating JWT Token", exception);
        }
    }

    public String getSubject(String jwtToken) {
        try{
            var algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtv = JWT.require(algorithm)
                    .withIssuer("LORDOFTHERINGS")
                    .build();
            return jwtv.verify(jwtToken).getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("JWT Token invalid or expired");
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
