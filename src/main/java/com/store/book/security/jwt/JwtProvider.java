package com.store.book.security.jwt;

import com.store.book.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;

    public JwtProvider(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public Date getExpireDate() {
        return new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24));
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .claim("userId", user.getId())
                .expiration(getExpireDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String removeBearer(String bearerToken) {
        int bearerLength = "bearer ".length();
        return bearerToken.substring(bearerLength);
    }

    public Claims getClaims(String token) {
        JwtParser jwtParser = Jwts.parser()
                .setSigningKey(key)
                .build();
        return jwtParser.parseClaimsJws(token).getPayload();
    }
}
