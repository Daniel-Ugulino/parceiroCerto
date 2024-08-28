//package com.example.ApiGateway.Config;
//
//import com.example.ApiGateway.Domain.Roles;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import java.util.Date;
//import java.util.function.Function;
//
//@Component
//public class TokenProvider {
//
//    @Value("${jwt.secret}")
//    public String secret;
//
//    @Value("${jwt.expiration}")
//    public Long accessTokenExpiration;
//
//
//    @Value("${jwt.refreshExpiration}")
//    public Long refreshTokenExpiration;
//
//
//    public String generateAccessToken(String email, Roles role) {
//        return generateToken(email, role, accessTokenExpiration);
//    }
//
//    public String generateRefreshToken(String email, Roles role) {
//        return generateToken(email, role, refreshTokenExpiration);
//    }
//
//    public String generateToken(String email, Roles role, long expirationTime) {
//        return Jwts.builder()
//                .setSubject(email)
//                .claim("role", role.name())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
//
//
//    public String extractEmail(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String email = extractEmail(token);
//        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//
//}
