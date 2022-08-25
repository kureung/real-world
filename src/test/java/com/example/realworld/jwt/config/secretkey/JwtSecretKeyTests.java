package com.example.realworld.jwt.config.secretkey;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

class JwtSecretKeyTests {

    @Test
    void 비밀키를_만들때_예외없이_잘_만들어진다() {
        String keyValue = "kureung";
        assertThatCode(() -> new JwtSecretKey(keyValue))
                .doesNotThrowAnyException();
    }

}