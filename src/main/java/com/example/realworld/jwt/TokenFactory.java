package com.example.realworld.jwt;

import com.example.realworld.domain.user.model.User;
import com.example.realworld.jwt.config.JwtConfig;
import com.example.realworld.web.token.Token;

public interface TokenFactory {

    Token createdTokenForFirstLogin(JwtConfig config, User user);

}
