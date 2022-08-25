package com.example.realworld.jwt;

import static com.example.realworld.web.exception.ErrorCode.NOT_VALID_TOKEN;

import com.example.realworld.jwt.config.JwtConfig;
import com.example.realworld.jwt.exception.NotValidTokenException;
import com.example.realworld.web.token.Token;
import com.example.realworld.web.token.TokenParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenParser implements TokenParser {

    private static final String EMAIL = "email";

    private final JwtConfig jwtConfig;

    public JwtTokenParser(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public String findEmailByToken(Token token) {
        verifyInvalidToken(token);
        Claims claims = parsedToken(token);
        return (String) claims.get(EMAIL);
    }

    @Override
    public void verifyValidToken(Token token) {
        if (Objects.isNull(token)) {
            throw new NotValidTokenException(NOT_VALID_TOKEN);
        }
    }

    private void verifyInvalidToken(Token token) {
    }

    private Claims parsedToken(Token token) {
        return Jwts.parser()
                .setSigningKey(token.toString())
                .parseClaimsJws("")
                .getBody();
    }

}
