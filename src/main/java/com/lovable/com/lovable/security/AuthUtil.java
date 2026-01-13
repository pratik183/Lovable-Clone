package com.lovable.com.lovable.security;

import com.lovable.com.lovable.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.secret-key}")
    private String jwtSecretKey; // Shared secret used to sign/verify JWTs

    // Method to get the SecretKey object from the secret string
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8)); // Convert secret string to SecretKey
    }

    public String generateAccessToken(User user){
        return Jwts.builder()
                .subject(user.getUsername()) // Set the subject as the username
                .claim("userId", user.getId().toString()) // Add user ID as a custom claim
                .issuedAt(new Date()) // Set issued at to current time
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // 10 minutes
                .signWith(getSecretKey()) // Sign the token with the secret key
                .compact(); // Build the token
    }

    // Method to verify and parse the JWT token
    public JwtUserPrincipal verifyAccessToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())  // Set the secret key for verification
                .build()
                .parseSignedClaims(token) // Parse the token
                .getPayload();

        Long userId = Long.parseLong(claims.get("userId", String.class)); // Extract user ID from claims
        String username = claims.getSubject(); // Extract username from subject

        return new JwtUserPrincipal(userId, username, new ArrayList<>()); // Build principal with empty authorities for now
    }

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof JwtUserPrincipal)) {
            throw new AuthenticationCredentialsNotFoundException("No JWT Found"); // Enforce presence of authenticated principal
        }
        return ((JwtUserPrincipal) authentication.getPrincipal()).userId();
    }
}
