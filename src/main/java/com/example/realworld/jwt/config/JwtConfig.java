package com.example.realworld.jwt.config;

import com.example.realworld.jwt.config.expirytime.ExpiryTime;
import java.security.PrivateKey;

public interface JwtConfig {

    PrivateKey secretKey();

    PrivateKey privateKey();

    ExpiryTime expiryTime();

    int expiryTimeValue();

}
