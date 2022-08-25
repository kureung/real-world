package com.example.realworld.jwt;

import static org.assertj.core.api.Assertions.assertThatCode;

import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.model.UserAccountInfo;
import com.example.realworld.jwt.config.JwtConfig;
import com.example.realworld.jwt.config.JwtKureungConfig;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class JwtTokenForFirstLoginTests {

    private final JwtConfig jwtConfig = JwtKureungConfig.instance();

    @Test
    void 첫_로그인을_할_때_토큰이_예외없이_생성된다() {
        assertThatCode(() -> new JwtTokenForFirstLogin(jwtConfig, user()))
                .doesNotThrowAnyException();
    }

    private User user() {
        UserAccountInfo accountInfo = UserAccountInfo.builder()
                .username("name")
                .email("abc@naver.com")
                .password("password")
                .build();

        return User.builder()
                .userAccountInfo(accountInfo)
                .bio("I like an apple")
                .image("www.naver.com")
                .followingEmails(new HashSet<>())
                .build();
    }

}