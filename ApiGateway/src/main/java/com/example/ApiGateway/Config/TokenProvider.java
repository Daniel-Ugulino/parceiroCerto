package com.example.ApiGateway.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class TokenProvider {

    @Value("${jwt.secret}")
    public String secret;

    @Value("${jwt.expiration}")
    public Long accessTokenExpiration;


    @Value("${jwt.refreshExpiration}")
    public Long refreshTokenExpiration;


    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    public List<String> extractRole(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return List.of((String) claims.get("role"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract roles from token", e);
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
