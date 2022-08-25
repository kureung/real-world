package com.example.realworld.jwt.config;

import com.example.realworld.jwt.config.expirytime.ExpiryTime;
import com.example.realworld.jwt.config.expirytime.JwtExpiryTime;
import com.example.realworld.jwt.config.secretkey.JwtSecretKey;
import java.security.PrivateKey;

public class JwtKureungConfig implements JwtConfig {

    private final PrivateKey privateKey = secretKey();

    private final ExpiryTime expiryTime = expiryTime();

    private JwtKureungConfig() {
    }

    public static JwtConfig instance() {
        return JwtConfigHolder.INSTANCE;
    }

    private static class JwtConfigHolder {

        private static final JwtConfig INSTANCE = new JwtKureungConfig();

    }

    @Override
    public PrivateKey secretKey() {
        return new JwtSecretKey("kureung");
    }

    @Override
    public ExpiryTime expiryTime() {
        return new JwtExpiryTime(30 * 60);
    }

    @Override
    public int expiryTimeValue() {
        return expiryTime.expiryTime();
    }

    @Override
    public PrivateKey privateKey() {
        return privateKey;
    }

}
