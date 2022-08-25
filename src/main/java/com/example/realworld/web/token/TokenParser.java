package com.example.realworld.web.token;

public interface TokenParser {

    String findEmailByToken(Token token);

    void verifyValidToken(Token token);

}
