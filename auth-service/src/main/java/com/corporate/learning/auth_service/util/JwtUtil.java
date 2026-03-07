package com.corporate.learning.auth_service.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationTime = 3600000; // 1 hour

    public String generateToken(String userId, String email) {
       return Jwts.builder()
               .setSubject(userId)
                .claim("email", email)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
               .compact();

    }

    public String validateToken(String token) {
        try {
            return io.jsonwebtoken.Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (io.jsonwebtoken.JwtException e) {
            return null; // Invalid token
        }
    }


}
