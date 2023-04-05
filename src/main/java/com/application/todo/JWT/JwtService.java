package com.application.todo.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    public String getUserFromToken(String token) {
        return getTokenClaims(token, Claims::getSubject);
    }

    public Date getExpDateFromToken(String token) {
        return getTokenClaims(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return getExpDateFromToken(token).before(new Date());
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        int expiration_Time = 1000 * 60 * 1;
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration_Time))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(userDetails.getUsername())
                .signWith(signInKey())
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public <T> T getTokenClaims(String token, Function<Claims, T> claimsFunction) {
        Claims claims = getAllClaims(token);
        return claimsFunction.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUserFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(signInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key signInKey() {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        byte[] key = decoder.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
}
