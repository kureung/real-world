package com.example.realworld.jwt;

import com.example.realworld.domain.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class JwtToken implements Token {

    private final static String EMAIL = "email";

    private final String tokenValue;

    private final JwtConfig jwtConfig;

    private JwtToken(String tokenValue) {
        this(tokenValue, null);
    }

    private JwtToken(String tokenValue, JwtConfig jwtConfig) {
        this.tokenValue = tokenValue;
        this.jwtConfig = jwtConfig;
    }

    public Token createToken(User user) {
        String email = Jwts.builder()
                .signWith(jwtConfig.getPrivateKey())
                .signWith(jwtConfig.getPublicKey())
                .claim("email", user.email())
                .compact();

        return null;
    }

    public String extractedUserEmail() {
        JwtParser jwtParser = jwtParser();
        Claims claims = claims(jwtParser);
        return (String) claims.get(EMAIL);
    }

    private static Claims claims(JwtParser jwtParser) {
        return jwtParser.parseClaimsJws("")
                .getBody();
    }

    private JwtParser jwtParser() {
        return Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getPrivateKey())
                .setSigningKey(jwtConfig.getPublicKey())
                .build();
    }

}
